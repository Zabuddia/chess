package service;

import dataAccess.*;
import request.CreateGameRequest;
import response.CreateGameResponse;

public class CreateGameService {
    private final AuthDAO authDAO = new SQLAuthDAO();
    private final GameDAO gameDAO = new SQLGameDAO();
    public boolean validateAuth(String authToken) {
        return authDAO.getAuth(authToken);
    }
    public CreateGameResponse createGame(String authToken, CreateGameRequest createGameRequest) {
        if (!validateAuth(authToken)) {
            return new CreateGameResponse("Error: unauthorized", null);
        }

        String gameName = createGameRequest.gameName();

        int gameID = gameDAO.createGame(gameName);
        return new CreateGameResponse("gameID", gameID);
    }
}
