package serviceTests;

import dataAccess.MemoryAuthDAO;
import model.AuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CreateGameService;

public class CreateGameServiceTests {

    @Test
    @DisplayName("Create Game")
    public void creteGameTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        String gameName = "game1";

        CreateGameService createGameService = new CreateGameService();
        int gameID = createGameService.createGame(authToken, gameName);

        Assertions.assertNotEquals(gameID, -1, "Did not create the game");
    }

    @Test
    @DisplayName("Unauthorized Create Game")
    public void unauthorizedCreateGameTest() {
        String username = "buddia";
        String authToken = "12345";
        String unauthorizedAuthToken = "54321";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        String gameName = "game1";

        CreateGameService createGameService = new CreateGameService();
        int gameID = createGameService.createGame(unauthorizedAuthToken, gameName);

        Assertions.assertEquals(gameID, -1, "Created game even when unauthorized");
    }
}
