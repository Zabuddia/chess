package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import request.ListGamesRequest;
import response.ListGamesResponse;

public class ListGamesService {
    public ListGamesResponse listGames(ListGamesRequest listGamesRequest) {
        String authToken = listGamesRequest.authToken();
        AuthDAO authDAO = new MemoryAuthDAO();
        if (!authDAO.getAuth(authToken)) {
            return new ListGamesResponse(401, "message", "Error: unauthorized", null);
        }

        GameDAO gameDAO = new MemoryGameDAO();

        return new ListGamesResponse(200, null, null, gameDAO.listGames());
    }
}
