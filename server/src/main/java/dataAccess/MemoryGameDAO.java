package dataAccess;

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
    public void createGame(GameData game) {
        gameList.add(game);
    }
}
