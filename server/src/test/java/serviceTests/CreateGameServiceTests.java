package serviceTests;

import dataAccess.*;
import model.AuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.CreateGameRequest;
import request.RegisterRequest;
import response.CreateGameResponse;
import response.RegisterResponse;
import service.CreateGameService;
import service.RegisterService;

public class CreateGameServiceTests {
    AuthDAO authDAO = new SQLAuthDAO();
    UserDAO userDAO = new SQLUserDAO();
    GameDAO gameDAO = new SQLGameDAO();
    @BeforeEach
    public void clearAll() {
        authDAO.clearAuth();
        userDAO.clearUser();
        gameDAO.clearGame();
    }
    @Test
    @DisplayName("Create Game")
    public void creteGameTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        String gameName = "game1";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameService createGameService = new CreateGameService();
        CreateGameResponse createGameResponse = createGameService.createGame(registerResponse.authToken(), createGameRequest);

        int gameID = createGameResponse.gameID();

        Assertions.assertNotEquals(gameID, -1, "Did not create the game");
    }

    @Test
    @DisplayName("Unauthorized Create Game")
    public void unauthorizedCreateGameTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        String gameName = "game1";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameService createGameService = new CreateGameService();
        createGameService.createGame("unauthorized", createGameRequest);

        Assertions.assertTrue(SQLDAO.isEmpty("game"), "Created game even when unauthorized");
    }
}
