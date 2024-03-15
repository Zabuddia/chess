package dataAccessTests;

import chess.ChessGame;
import dataAccess.*;
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

public class SQLGameDAOTests {
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
    @DisplayName("Clear Game Test")
    public void clearAuthTest() {
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

        gameDAO.clearGame();
        Assertions.assertTrue(SQLDAO.isEmpty("game"), "game was not cleared");
    }
    @Test
    @DisplayName("Create Game Positive Test")
    public void createGamePositiveTest() {
        String gameName = "game1";
        gameDAO.createGame(gameName);
        Assertions.assertFalse(SQLDAO.isEmpty("game"), "game was not created");
    }
    //    @Test
//    @DisplayName("Create Game Negative Test")
//    public void createGameNegativeTest() {
//        String gameName = "game1";
//        gameDAO.createGame(gameName);
//        gameDAO.createGame(gameName);
//        Assertions.assertEquals(1, SQLDAO.tableSize("game"), "game was created");
//    }
    @Test
    @DisplayName("List Games Positive Test")
    public void listGamesPositiveTest() {
        String gameName = "game1";
        String gameName2 = "game2";
        gameDAO.createGame(gameName);
        gameDAO.createGame(gameName2);
        Assertions.assertEquals(2, gameDAO.listGames().size(), "game was not listed");
    }
    @Test
    @DisplayName("List Games Negative Test")
    public void listGamesNegativeTest() {
        String gameName = "game1";
        String gameName2 = "game2";
        gameDAO.createGame(gameName);
        gameDAO.createGame(gameName2);
        gameDAO.clearGame();
        Assertions.assertEquals(0, gameDAO.listGames().size(), "game was listed");
    }
    @Test
    @DisplayName("Get Game Positive Test")
    public void getGamePositiveTest() {
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

        Assertions.assertTrue(gameDAO.getGame(gameID), "game was not found");
    }
    @Test
    @DisplayName("Get Game Negative Test")
    public void getGameNegativeTest() {
        String gameName = "game1";
        gameDAO.createGame(gameName);
        Assertions.assertFalse(gameDAO.getGame(2), "game was found");
    }
    @Test
    @DisplayName("Update Game Positive Test")
    public void updateGamePositiveTest() {
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

        String result = gameDAO.updateGame(ChessGame.TeamColor.WHITE, gameID, "buddia");
        Assertions.assertEquals("Successfully updated game", result, "game was not updated");
    }
    @Test
    @DisplayName("Update Game Negative Test")
    public void updateGameNegativeTest() {
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

        String result = gameDAO.updateGame(ChessGame.TeamColor.WHITE, gameID + 1, "buddia");
        Assertions.assertEquals("No game with that ID", result, "game was updated");
    }
}