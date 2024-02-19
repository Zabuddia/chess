package dataAccess;

import model.AuthData;
import model.GameData;

public interface GameDAO {
    void clearGame();

    void createGame(GameData game);
}
