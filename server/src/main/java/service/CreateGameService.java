package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import request.CreateGameRequest;
import response.CreateGameResponse;

public class CreateGameService {
    private final AuthDAO authDAO = new MemoryAuthDAO();
    private final GameDAO gameDAO = new MemoryGameDAO();
    public boolean validateAuth(String authToken) {
        return authDAO.getAuth(authToken);
    }
    public CreateGameResponse createGame(String authToken, CreateGameRequest createGameRequest) {
        if (!validateAuth(authToken)) {
            return new CreateGameResponse("Error: unauthorized", -1);
        }

        String gameName = createGameRequest.gameName();

        int gameID = gameDAO.createGame(gameName);
        return new CreateGameResponse("gameID", gameID);
    }
}
