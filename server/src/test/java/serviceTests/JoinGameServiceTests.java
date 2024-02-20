package serviceTests;

import chess.ChessGame;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.JoinGameService;

public class JoinGameServiceTests {

    @Test
    @DisplayName("Join Game With Color")
    public void joinGameWithColorTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        int gameID = 1111;
        String whiteUsername = "waffleiron";
        String gameName = "game1";
        ChessGame game = new ChessGame();

        GameData gameData = new GameData(gameID, whiteUsername, null, gameName, game);
        GameData expectedGameData = new GameData(gameID, whiteUsername, username, gameName, game);

        MemoryGameDAO.gameList.add(gameData);

        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(authToken, ChessGame.TeamColor.BLACK, gameID);

        Assertions.assertEquals(result, "Successfully updated game", "Did not update game");
        Assertions.assertTrue(MemoryGameDAO.gameList.contains(expectedGameData), "Did not update game correctly");
    }

    @Test
    @DisplayName("Join Game Without Color")
    public void joinGameWithoutColorTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        int gameID = 1111;
        String whiteUsername = "waffleiron";
        String gameName = "game1";
        ChessGame game = new ChessGame();

        GameData gameData = new GameData(gameID, whiteUsername, null, gameName, game);
        GameData expectedGameData = new GameData(gameID, whiteUsername, null, gameName, game);

        MemoryGameDAO.gameList.add(gameData);

        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(authToken, null, gameID);

        Assertions.assertEquals(result, "Successfully updated game", "Did not update game");
        Assertions.assertTrue(MemoryGameDAO.gameList.contains(expectedGameData), "Did not update game correctly");
    }

    @Test
    @DisplayName("Unauthorized Join Game")
    public void unauthorizedJoinGameTest() {
        String username = "buddia";
        String authToken = "12345";
        String unauthorizedAuthToken = "54321";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        int gameID = 1111;
        String whiteUsername = "waffleiron";
        String gameName = "game1";
        ChessGame game = new ChessGame();

        GameData gameData = new GameData(gameID, whiteUsername, null, gameName, game);
        GameData expectedGameData = new GameData(gameID, whiteUsername, username, gameName, game);

        MemoryGameDAO.gameList.add(gameData);

        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(unauthorizedAuthToken, ChessGame.TeamColor.BLACK, gameID);

        Assertions.assertEquals(result, "Unauthorized", "Did not recognize that the user is unauthorized");
        Assertions.assertFalse(MemoryGameDAO.gameList.contains(expectedGameData), "Joined game without authorization");
    }

    @Test
    @DisplayName("Join Game Already Taken")
    public void alreadyTakenTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        int gameID = 1111;
        String whiteUsername = "waffleiron";
        String gameName = "game1";
        ChessGame game = new ChessGame();

        GameData gameData = new GameData(gameID, whiteUsername, null, gameName, game);
        GameData unexpectedGameData = new GameData(gameID, username, null, gameName, game);

        MemoryGameDAO.gameList.add(gameData);

        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(authToken, ChessGame.TeamColor.WHITE, gameID);

        Assertions.assertEquals(result, "Already taken", "Did not recognize that the color was already taken");
        Assertions.assertFalse(MemoryGameDAO.gameList.contains(unexpectedGameData), "Updated game even though color was already taken");
    }

    @Test
    @DisplayName("GameID doesn't exist")
    public void gameIDDoesntExistTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        int gameID = 1111;
        int wrongGameID = 2222;
        String whiteUsername = "waffleiron";
        String gameName = "game1";
        ChessGame game = new ChessGame();

        GameData gameData = new GameData(gameID, whiteUsername, null, gameName, game);
        GameData unexpectedGameData = new GameData(wrongGameID, whiteUsername, username, gameName, game);

        MemoryGameDAO.gameList.add(gameData);

        JoinGameService joinGameService = new JoinGameService();
        String result = joinGameService.joinGame(authToken, ChessGame.TeamColor.BLACK, wrongGameID);

        Assertions.assertEquals(result, "No game with that ID", "Did not recognize that there is no game with that ID");
        Assertions.assertFalse(MemoryGameDAO.gameList.contains(unexpectedGameData), "Updated game to incorrect ID");
    }
}
