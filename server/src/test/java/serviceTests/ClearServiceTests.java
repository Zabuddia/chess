package serviceTests;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.ClearRequest;
import request.CreateGameRequest;
import request.RegisterRequest;
import response.RegisterResponse;
import service.ClearService;
import service.CreateGameService;
import service.RegisterService;

public class ClearServiceTests {
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
    @DisplayName("Clear Service Test")
    public void clearTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        String gameName = "game1";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameService createGameService = new CreateGameService();
        createGameService.createGame(registerResponse.authToken(), createGameRequest);

        ClearRequest clearRequest = new ClearRequest(true);
        ClearService clearService = new ClearService();
        clearService.clear(clearRequest);

        Assertions.assertTrue(SQLDAO.isEmpty("user"), "user is not empty");
        Assertions.assertTrue(SQLDAO.isEmpty("auth"), "auth is not empty");
        Assertions.assertTrue(SQLDAO.isEmpty("game"), "game is not empty");
    }
}