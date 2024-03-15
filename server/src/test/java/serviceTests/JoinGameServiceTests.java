package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.CreateGameRequest;
import request.JoinGameRequest;
import request.RegisterRequest;
import response.CreateGameResponse;
import response.RegisterResponse;
import service.CreateGameService;
import service.JoinGameService;
import service.RegisterService;

public class JoinGameServiceTests {
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
    @DisplayName("Join Game With Color")
    public void joinGameWithColorTest() {
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

        JoinGameRequest joinGameRequest = new  JoinGameRequest(ChessGame.TeamColor.BLACK, gameID);
        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(joinGameRequest, registerResponse.authToken()).message();

        Assertions.assertNull(result, "Did not update game");
    }

    @Test
    @DisplayName("Join Game Without Color")
    public void joinGameWithoutColorTest() {
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

        JoinGameRequest joinGameRequest = new  JoinGameRequest(null, gameID);
        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(joinGameRequest, registerResponse.authToken()).message();

        Assertions.assertNull(result, "Did not update game");
    }

    @Test
    @DisplayName("Unauthorized Join Game")
    public void unauthorizedJoinGameTest() {
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

        JoinGameRequest joinGameRequest = new  JoinGameRequest(ChessGame.TeamColor.BLACK, gameID);
        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(joinGameRequest, "unauthorized").message();

        Assertions.assertEquals(result, "Error: unauthorized", "Did not recognize that the user is unauthorized");
    }

    @Test
    @DisplayName("Join Game Already Taken")
    public void alreadyTakenTest() {
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

        JoinGameRequest joinGameRequest = new  JoinGameRequest(ChessGame.TeamColor.BLACK, gameID);
        JoinGameService joinGameService = new JoinGameService();
        joinGameService.joinGame(joinGameRequest, registerResponse.authToken()).message();

        String username2 = "hi";
        String password2 = "54321";
        String email2 = "fife.alan@gmail.com";

        RegisterRequest registerRequest2 = new RegisterRequest(username2, password2, email2);
        RegisterService registerService2 = new RegisterService();
        RegisterResponse registerResponse2 = registerService2.register(registerRequest2);

        CreateGameRequest createGameRequest2 = new CreateGameRequest(gameName);
        CreateGameService createGameService2 = new CreateGameService();
        createGameService2.createGame(registerResponse2.authToken(), createGameRequest2);

        JoinGameRequest joinGameRequest2 = new  JoinGameRequest(ChessGame.TeamColor.BLACK, gameID);
        JoinGameService joinGameService2 = new JoinGameService();
        String result = joinGameService2.joinGame(joinGameRequest2, registerResponse2.authToken()).message();

        Assertions.assertEquals(result, "Error: already taken", "Did not recognize that the color was already taken");
    }

    @Test
    @DisplayName("GameID doesn't exist")
    public void gameIDDoesntExistTest() {
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

        JoinGameRequest joinGameRequest = new  JoinGameRequest(ChessGame.TeamColor.BLACK, 99);
        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(joinGameRequest, registerResponse.authToken()).message();

        Assertions.assertEquals(result, "Error: bad request", "Did not recognize that there is no game with that ID");
    }
}