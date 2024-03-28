package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;

public interface GameDAO {
    void clearGame();

    int createGame(String gameName);

    ArrayList<GameData> listGames();

    boolean getGame(int gameID);

    String updateGame(ChessGame.TeamColor clientColor, int gameID, String username);

    void moveGame(int gameID, ChessGame game);
}