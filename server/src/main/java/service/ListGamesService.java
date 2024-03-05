package service;

import dataAccess.*;
import request.ListGamesRequest;
import response.ListGamesResponse;

public class ListGamesService {
    private final AuthDAO authDAO = new SQLAuthDAO();
    private final GameDAO gameDAO = new SQLGameDAO();
    public boolean validateAuth(String authToken) {
        return authDAO.getAuth(authToken);
    }
    public ListGamesResponse listGames(ListGamesRequest listGamesRequest, String authToken) {
        if (!validateAuth(authToken)) {
            return new ListGamesResponse("Error: unauthorized", null);
        }
        return new ListGamesResponse(null, gameDAO.listGames());
    }
}
