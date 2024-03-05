package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;

public class SQLGameDAO implements GameDAO {
    public void clearGame() {

    }

    public int createGame(String gameName) {
        return 0;
    }

    public ArrayList<GameData> listGames() {
        return null;
    }

    public boolean getGame(int gameID) {
        return false;
    }

    public String updateGame(ChessGame.TeamColor clientColor, int gameID, String username) {
        return null;
    }
}
