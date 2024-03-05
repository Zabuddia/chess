package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryGameDAO implements GameDAO {
    public static ArrayList<GameData> gameList = new ArrayList<>();
    public void clearGame() {
        gameList.clear();
    }
    public int createGame(String gameName) {
        ChessGame newGame = new ChessGame();
        GameData game = new GameData(gameList.size() + 1, null, null, gameName, newGame);
        gameList.add(game);
        return gameList.size();
    }
    public ArrayList<GameData> listGames() {
        return gameList;
    }
    public boolean getGame(int gameID) {
        for (GameData game : gameList) {
            if (game.gameID() == gameID) {
                return true;
            }
        }
        return false;
    }
    public String updateGame(ChessGame.TeamColor clientColor, int gameID, String username) {
        GameData gameToUpdate = null;
        for (GameData game : gameList) {
            if (game.gameID() == gameID) {
                gameToUpdate = game;
            }
        }

        if (gameToUpdate == null) {
            return "No game with that ID";
        }

        String whiteUsername = gameToUpdate.whiteUsername();
        String blackUsername = gameToUpdate.blackUsername();
        String gameName = gameToUpdate.gameName();
        ChessGame game = gameToUpdate.game();

        if (clientColor == ChessGame.TeamColor.WHITE && whiteUsername == null) {
            whiteUsername = username;
        } else if (clientColor == ChessGame.TeamColor.WHITE) {
            return "Already taken";
        }

        if (clientColor == ChessGame.TeamColor.BLACK && blackUsername == null) {
            blackUsername = username;
        } else if (clientColor == ChessGame.TeamColor.BLACK) {
            return "Already taken";
        }

        gameList.remove(gameToUpdate);

        GameData newGame = new GameData(gameID, whiteUsername, blackUsername, gameName, game);

        gameList.add(newGame);

        return "Successfully updated game";
    }
}
