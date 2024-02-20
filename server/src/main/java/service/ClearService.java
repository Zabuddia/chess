package service;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
import request.ClearRequest;
import response.ClearResponse;

public class ClearService {
    public ClearResponse clear(ClearRequest clearRequest) {
        MemoryUserDAO userDAO = new MemoryUserDAO();
        MemoryAuthDAO authDAO = new MemoryAuthDAO();
        MemoryGameDAO gameDAO = new MemoryGameDAO();

        userDAO.clearUser();
        authDAO.clearAuth();
        gameDAO.clearGame();

        return new ClearResponse(200, null, null);
    }
}
