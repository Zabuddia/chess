package service;

import dataAccess.*;
import request.ClearRequest;
import response.ClearResponse;

public class ClearService {
    private final UserDAO userDAO = new MemoryUserDAO();
    private final AuthDAO authDAO = new MemoryAuthDAO();
    private final GameDAO gameDAO = new MemoryGameDAO();
    public ClearResponse clear(ClearRequest clearRequest) {

        userDAO.clearUser();
        authDAO.clearAuth();
        gameDAO.clearGame();

        return new ClearResponse(null);
    }
}
