package dataAccess;

import model.AuthData;
import model.GameData;

import java.util.Collection;

public interface GameDAO {
    void clearGame();

    void createGame(String gameName);

    Collection<GameData> listGames();
}
