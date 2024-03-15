package serviceTests;

import dataAccess.*;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.CreateGameRequest;
import request.ListGamesRequest;
import request.RegisterRequest;
import response.RegisterResponse;
import service.CreateGameService;
import service.ListGamesService;
import service.RegisterService;

import java.util.ArrayList;

public class ListGamesServiceTests {
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
    @DisplayName("List Games")
    public void listGamesTest() {
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

        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesService listGamesService = new ListGamesService();

        ArrayList<GameData> listOfGames = listGamesService.listGames(listGamesRequest, registerResponse.authToken()).games();

        Assertions.assertNotNull(listOfGames, "Did not send the list of games");
    }

    @Test
    @DisplayName("Unauthorized List Games")
    public void unauthorizedListGamesTest() {
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

        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesService listGamesService = new ListGamesService();

        ArrayList<GameData> listOfGames = listGamesService.listGames(listGamesRequest, "unauthorized").games();

        Assertions.assertNull(listOfGames, "Sent listOfGames even when unauthorized");
    }
}