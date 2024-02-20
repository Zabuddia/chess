package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import model.GameData;

import java.util.Collection;

public class ListGamesService {
    public Collection<GameData> listGames(String authToken) {
        AuthDAO authDAO = new MemoryAuthDAO();
        if (!authDAO.getAuth(authToken)) {
            return null;
        }

        GameDAO gameDAO = new MemoryGameDAO();

        return gameDAO.listGames();
    }
}
