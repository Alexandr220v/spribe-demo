package service;

import api.BaseApi;
import dto.DeletePlayerResponseDTO;
import dto.GetPlayerRequestDTO;
import dto.GetPlayerResponseDTO;
import dto.PlayerRequestDTO;
import dto.PlayerResponseDTO;
import enums.Role;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RequestManager;
import static java.lang.String.format;

public class PlayerController extends BaseApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);
    private final String CREATE_PLAYER_PATH = "/player/create/%s";
    private final String UPDATE_PLAYER_PATH = "/player/update/%s/%s";
    private final String DELETE_PLAYER_PATH = "/player/delete/%s";
    private final String GET_PLAYER_PATH = "/player/get";

    public PlayerResponseDTO createValidPlayerByUser(Role userRole, PlayerRequestDTO playerRequestDTOPayload) {
        LOGGER.info("Creating player '{}' by editor: {}",
                playerRequestDTOPayload.getLogin(), userRole.getValue());

        return buildCreatePlayerRequest(playerRequestDTOPayload)
                .when()
                .get(format(CREATE_PLAYER_PATH, userRole.getValue()))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(PlayerResponseDTO.class);
    }
    public Response createInvalidPlayerByUser(Role userRole, PlayerRequestDTO playerRequestDTOPayload) {
        LOGGER.info("Creating player '{}' by editor: {}",
                playerRequestDTOPayload.getLogin(), userRole.getValue());

        return buildCreatePlayerRequest(playerRequestDTOPayload)
                .when()
                .get(format(CREATE_PLAYER_PATH, userRole.getValue()))
                .then()
                .extract()
                .response();
    }

    public PlayerResponseDTO updatePlayerByUser(Role userRole, int playerId, PlayerRequestDTO playerRequestDTOPayload) {
        LOGGER.info("Updating player '{}' (ID: {}) by editor: {}", playerRequestDTOPayload.getLogin(), playerId, userRole.getValue());

        return buildUpdatePlayerRequest(playerRequestDTOPayload)
                .when()
                .patch(format(UPDATE_PLAYER_PATH, userRole.getValue(), playerId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(PlayerResponseDTO.class);
    }
    public DeletePlayerResponseDTO deletePlayerByUser(Role userRole, GetPlayerRequestDTO getPlayerRequestDTO) {
        RestAssured.defaultParser = Parser.JSON;
        LOGGER.info("Deleting player ID '" + getPlayerRequestDTO.getPlayerId() + "' by user: " + userRole.getValue());
        return RequestManager.getRequestSpecification(BASE_URL)
                .contentType(ContentType.JSON)
                .body(getPlayerRequestDTO)
                .when()
                .delete(format(DELETE_PLAYER_PATH, userRole.getValue())).then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .as(DeletePlayerResponseDTO.class);
    }

    public GetPlayerResponseDTO getPlayer(int playerId) {
        LOGGER.info("Get player ID '" + playerId + "' details.");
        GetPlayerRequestDTO getPlayerRequestDTO = new GetPlayerRequestDTO();
        getPlayerRequestDTO.setPlayerId(playerId);
        return RequestManager.getRequestSpecification(BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam("playerId", playerId)
                .body(getPlayerRequestDTO)
                .when()
                .post(GET_PLAYER_PATH).then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(GetPlayerResponseDTO.class);
    }

    private RequestSpecification buildCreatePlayerRequest(PlayerRequestDTO payload) {
        Map<String, Object> queryParameters = Map.of(
                "age", payload.getAge(),
                "gender", payload.getGender(),
                "login", payload.getLogin(),
                "password", payload.getPassword(),
                "role", payload.getRole(),
                "screenName", payload.getScreenName()
        );
        return RequestManager.getRequestSpecification(BASE_URL)
                .log().uri()
                .contentType(ContentType.JSON)
                .log().headers()
                .queryParams(queryParameters);
    }

    private RequestSpecification buildUpdatePlayerRequest(PlayerRequestDTO payload) {

        return RequestManager.getRequestSpecification(BASE_URL)
                .log().uri()
                .contentType(ContentType.JSON)
                .log().headers()
                .body(payload)
                .log().body();
    }
}
