package player_tests;

import dto.DeletePlayerResponseDTO;
import dto.GetPlayerRequestDTO;
import dto.GetPlayerResponseDTO;
import dto.PlayerRequestDTO;
import dto.PlayerResponseDTO;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import service.PlayerController;
import utils.DataGenerator;

import static assertion.PlayerAssertion.validatePlayer;
import static enums.Gender.FEMALE;
import static enums.Gender.INVALID;
import static enums.Gender.MALE;
import static enums.Role.ADMIN;
import static enums.Role.SUPERVISOR;
import static enums.Role.USER;
import static org.testng.Assert.assertEquals;

public class PlayerControllerTests {

    // --- POSITIVE TESTS ---

    @Test(description = "Create a new 'player' by 'admin'.")
    public void verifyCreatePlayerByAdmin() {
        PlayerRequestDTO playerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin())
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateAge())
                .role(USER)
                .screenName(DataGenerator.generateScreenName())
                .gender(MALE)
                .build();
        PlayerController playerController = new PlayerController();
        PlayerResponseDTO playerResponseDTO = playerController.createValidPlayerByUser(ADMIN, playerRequestDTO);
        validatePlayer(playerRequestDTO, playerResponseDTO);
    }

    @Test(description = "Create a new 'player' by 'supervisor'.")
    public void verifyCreatePlayerBySupervisor() {
        PlayerRequestDTO playerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin())
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateAge())
                .role(USER)
                .screenName(DataGenerator.generateScreenName())
                .gender(MALE)
                .build();
        PlayerController playerController = new PlayerController();
        PlayerResponseDTO playerResponseDTO = playerController.createValidPlayerByUser(SUPERVISOR, playerRequestDTO);
        validatePlayer(playerRequestDTO, playerResponseDTO);
    }

    @Test(description = "Delete created a new player by supervisor.")
    public void verifyPlayerDeletionBySupervisor() {
        PlayerRequestDTO playerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin())
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateAge())
                .role(USER)
                .screenName(DataGenerator.generateScreenName())
                .gender(MALE)
                .build();
        PlayerController playerController = new PlayerController();
        PlayerResponseDTO playerResponseDTO = playerController.createValidPlayerByUser(ADMIN, playerRequestDTO);
        int playerId = playerResponseDTO.getId();
        GetPlayerRequestDTO getPlayerRequestDTO = new GetPlayerRequestDTO();
        getPlayerRequestDTO.setPlayerId(playerId);
        DeletePlayerResponseDTO deletePlayerResponseDTO = playerController.deletePlayerByUser(SUPERVISOR,getPlayerRequestDTO);
        assertEquals("{}",deletePlayerResponseDTO.getBody(),"Body  is not empty");
        assertEquals("100 CONTINUE",deletePlayerResponseDTO.getStatusCode(),"Status Code value  is different");
        GetPlayerResponseDTO getPlayerResponseActual = playerController.getPlayer(playerId);
        assertEquals(getPlayerResponseActual, "", "Player exists : " + getPlayerResponseActual.getLogin());

    }

    @Test(description = "Update created  player by admin.")
    public void verifyPlayerUpdateBySupervisor() {
        final String UPDATED = "updated";
        PlayerRequestDTO createPlayerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin())
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateAge())
                .role(USER)
                .screenName(DataGenerator.generateScreenName())
                .gender(MALE)
                .build();
        PlayerController playerController = new PlayerController();
        PlayerResponseDTO playerResponseDTO = playerController.createValidPlayerByUser(SUPERVISOR, createPlayerRequestDTO);
        int playerId = playerResponseDTO.getId();
        GetPlayerRequestDTO getPlayerRequestDTO = new GetPlayerRequestDTO();
        getPlayerRequestDTO.setPlayerId(playerId);
        PlayerRequestDTO updatePlayerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin() + UPDATED)
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateAge() + 1)
                .role(USER)
                .screenName(DataGenerator.generateScreenName() + UPDATED)
                .gender(FEMALE)
                .build();
        PlayerResponseDTO updatePlayerResponseDTO = playerController.updatePlayerByUser(ADMIN,playerId,updatePlayerRequestDTO);
        validatePlayer(updatePlayerRequestDTO, updatePlayerResponseDTO);
    }

    // --- NEGATIVE TESTS ---
    @Test(description = "Create a new 'player' by supervisor with invalid age")
    public void verifyCreatePlayerByAdminWithInvalidAge() {
        PlayerRequestDTO playerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin())
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateInvalidAge())
                .role(USER)
                .screenName(DataGenerator.generateScreenName())
                .gender(MALE)
                .build();
        PlayerController playerController = new PlayerController();
        Response playerResponseDTO = playerController.createInvalidPlayerByUser(SUPERVISOR, playerRequestDTO);
        assertEquals(playerResponseDTO.statusCode(), HttpStatus.SC_NOT_FOUND, "Invalid player creation should have status code: " + HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "Create a new 'player' by admin with invalid gender")
    public void verifyCreatePlayerByAdminWithInvalidGender() {
        PlayerRequestDTO playerRequestDTO = new  PlayerRequestDTO.Builder()
                .login(DataGenerator.generateLogin())
                .password(DataGenerator.generatePassword())
                .age(DataGenerator.generateAge())
                .role(USER)
                .screenName(DataGenerator.generateScreenName())
                .gender(INVALID)
                .build();
        PlayerController playerController = new PlayerController();
        Response playerResponseDTO = playerController.createInvalidPlayerByUser(ADMIN, playerRequestDTO);
        assertEquals(playerResponseDTO.statusCode(), HttpStatus.SC_NOT_FOUND, "Invalid player creation should have status code: " + HttpStatus.SC_NOT_FOUND);
    }


}
