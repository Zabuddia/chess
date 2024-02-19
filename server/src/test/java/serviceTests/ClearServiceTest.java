package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ClearService;

public class ClearServiceTest {

    @Test
    @DisplayName("Clear Service Test")
    public void clearTest() {
        ChessGame chessGame = new ChessGame();
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        GameData game = new GameData(1111, "buddia", "waffleiron", "game1", chessGame);
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        userDAO.createUser(username, password, email);
        authDAO.createAuth(username);
        gameDAO.createGame(game);

        ClearService clearService = new ClearService();
        clearService.clear();

        Assertions.assertTrue(MemoryUserDAO.userList.isEmpty(), "UserList is not empty");
        Assertions.assertTrue(MemoryAuthDAO.authList.isEmpty(), "AuthList is not empty");
        Assertions.assertTrue(MemoryGameDAO.gameList.isEmpty(), "GameList is not empty");
    }
}
