package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import request.ListGamesRequest;
import response.ListGamesResponse;

public class ListGamesService {
    private final AuthDAO authDAO = new MemoryAuthDAO();
    private final GameDAO gameDAO = new MemoryGameDAO();
    public boolean validateAuth(String authToken) {
        return authDAO.getAuth(authToken);
    }
    public ListGamesResponse listGames(ListGamesRequest listGamesRequest, String authToken) {
        if (!validateAuth(authToken)) {
            return new ListGamesResponse("message", "Error: unauthorized", null);
        }
        return new ListGamesResponse(null, null, gameDAO.listGames());
    }
}
