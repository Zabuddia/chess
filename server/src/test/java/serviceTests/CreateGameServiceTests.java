package serviceTests;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
import model.AuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.CreateGameRequest;
import service.CreateGameService;

public class CreateGameServiceTests {

    @Test
    @DisplayName("Create Game")
    public void creteGameTest() {
        MemoryAuthDAO.authList.clear();
        MemoryUserDAO.userList.clear();
        MemoryGameDAO.gameList.clear();

        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        String gameName = "game1";

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameService createGameService = new CreateGameService();
        int gameID = createGameService.createGame(authToken, createGameRequest).gameID();

        Assertions.assertNotEquals(gameID, -1, "Did not create the game");
    }

    @Test
    @DisplayName("Unauthorized Create Game")
    public void unauthorizedCreateGameTest() {
        MemoryAuthDAO.authList.clear();
        MemoryUserDAO.userList.clear();
        MemoryGameDAO.gameList.clear();

        String username = "buddia";
        String authToken = "12345";
        String unauthorizedAuthToken = "54321";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        String gameName = "game1";

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameService createGameService = new CreateGameService();
        Integer gameID = createGameService.createGame(unauthorizedAuthToken, createGameRequest).gameID();

        Assertions.assertNull(gameID, "Created game even when unauthorized");
    }
}
