package assertion;

import dto.PlayerRequestDTO;
import dto.PlayerResponseDTO;
import org.testng.asserts.SoftAssert;

public class PlayerAssertion {
    public static void validatePlayer(PlayerRequestDTO playerRequestDTO, PlayerResponseDTO playerResponseDTO) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(playerRequestDTO.getLogin(), playerResponseDTO.getLogin(),"Player login is different.");
        softAssert.assertEquals(playerRequestDTO.getGender(), playerResponseDTO.getGender(), "Player gender is different.");
        softAssert.assertEquals(playerRequestDTO.getAge(), playerResponseDTO.getAge(), "Player age is different.");
        softAssert.assertEquals(playerRequestDTO.getRole(), playerResponseDTO.getRole(), "Player role is different.");
        softAssert.assertEquals(playerRequestDTO.getScreenName(), playerResponseDTO.getScreenName(), "Player screen name is different.");
        softAssert.assertNotNull(playerResponseDTO.getId(), "Player ID should be generated.");
        softAssert.assertAll();
    }
}
