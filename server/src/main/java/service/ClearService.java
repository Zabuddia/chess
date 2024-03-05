package service;

import dataAccess.*;
import request.ClearRequest;
import response.ClearResponse;

public class ClearService {
    private final UserDAO userDAO = new SQLUserDAO();
    private final AuthDAO authDAO = new SQLAuthDAO();
    private final GameDAO gameDAO = new SQLGameDAO();
    public ClearResponse clear(ClearRequest clearRequest) {

        userDAO.clearUser();
        authDAO.clearAuth();
        gameDAO.clearGame();

        return new ClearResponse(null);
    }
}
