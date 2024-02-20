package serviceTests;

import dataAccess.*;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ListGamesService;

import java.util.Collection;

public class ListGamesServiceTests {

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

        ListGamesService listGamesService = new ListGamesService();

        Collection<GameData> listOfGames = listGamesService.listGames(authToken);

        Assertions.assertFalse(listOfGames.isEmpty(), "Did not send the list of games");
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

        ListGamesService listGamesService = new ListGamesService();

        Collection<GameData> listOfGames = listGamesService.listGames(unauthorizedAuthToken);

        Assertions.assertNull(listOfGames, "Sent listOfGames even when unauthorized");
    }
}
