package dataAccess;

import chess.ChessGame;
import model.AuthData;
import model.GameData;

import java.util.Collection;

public interface GameDAO {
    void clearGame();

    int createGame(String gameName);

    Collection<GameData> listGames();

    boolean getGame(int gameID);

    String updateGame(ChessGame.TeamColor clientColor, int gameID, String username);
}
