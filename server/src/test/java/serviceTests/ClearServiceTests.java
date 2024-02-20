package serviceTests;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ClearService;

public class ClearServiceTests {

    @Test
    @DisplayName("Clear Service Test")
    public void clearTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        String gameName = "game1";
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        userDAO.createUser(username, password, email);
        authDAO.createAuth(username);
        gameDAO.createGame(gameName);

        ClearService clearService = new ClearService();
        clearService.clear();

        Assertions.assertTrue(MemoryUserDAO.userList.isEmpty(), "UserList is not empty");
        Assertions.assertTrue(MemoryAuthDAO.authList.isEmpty(), "AuthList is not empty");
        Assertions.assertTrue(MemoryGameDAO.gameList.isEmpty(), "GameList is not empty");
    }
}