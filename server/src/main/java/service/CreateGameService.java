package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;

public class CreateGameService {
    public int createGame(String authToken, String gameName) {
        AuthDAO authDAO = new MemoryAuthDAO();
        if (!authDAO.getAuth(authToken)) {
            return -1;
        }

        GameDAO gameDAO = new MemoryGameDAO();
        return gameDAO.createGame(gameName);
    }
}
