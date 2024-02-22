package serviceTests;

import dataAccess.*;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.ListGamesRequest;
import service.ListGamesService;

import java.util.ArrayList;

public class ListGamesServiceTests {
    @BeforeEach
    public void clearAll() {
        MemoryAuthDAO.authList.clear();
        MemoryUserDAO.userList.clear();
        MemoryGameDAO.gameList.clear();
    }
    @Test
    @DisplayName("List Games")
    public void listGamesTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        String gameName = "game1";
        GameDAO gameDAO = new MemoryGameDAO();

        gameDAO.createGame(gameName);

        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesService listGamesService = new ListGamesService();

        ArrayList<GameData> listOfGames = listGamesService.listGames(listGamesRequest, authToken).games();

        Assertions.assertNotNull(listOfGames, "Did not send the list of games");
    }

    @Test
    @DisplayName("Unauthorized List Games")
    public void unauthorizedListGamesTest() {
        String username = "buddia";
        String authToken = "12345";
        String unauthorizedAuthToken = "54321";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        String gameName = "game1";
        GameDAO gameDAO = new MemoryGameDAO();

        gameDAO.createGame(gameName);

        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesService listGamesService = new ListGamesService();

        ArrayList<GameData> listOfGames = listGamesService.listGames(listGamesRequest, unauthorizedAuthToken).games();

        Assertions.assertNull(listOfGames, "Sent listOfGames even when unauthorized");
    }
}
