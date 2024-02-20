package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import response.CreateGameResponse;

public class CreateGameService {
    public CreateGameResponse createGame(String authToken, String gameName) {
        AuthDAO authDAO = new MemoryAuthDAO();
        if (!authDAO.getAuth(authToken)) {
            return new CreateGameResponse(401, "message", "Error: unauthorized",-1);
        }

        GameDAO gameDAO = new MemoryGameDAO();
        int gameID = gameDAO.createGame(gameName);
        return new CreateGameResponse(200, "gameID", null, gameID);
    }
}
