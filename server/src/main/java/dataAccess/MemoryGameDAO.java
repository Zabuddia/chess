package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryGameDAO implements GameDAO {
    public static Collection<GameData> gameList = new ArrayList<>();
    @Override
    public void clearGame() {
        gameList.clear();
    }

    @Override
    public void createGame(String gameName) {
        ChessGame newGame = new ChessGame();
        GameData game = new GameData(gameList.size(), null, null, gameName, newGame);
        gameList.add(game);
    }

    @Override
    public Collection<GameData> listGames() {
        return gameList;
    }
}
