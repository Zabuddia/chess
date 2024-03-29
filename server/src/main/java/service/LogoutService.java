package service;

import dataAccess.AuthDAO;
import dataAccess.SQLAuthDAO;
import request.LogoutRequest;
import response.LogoutResponse;

public class LogoutService {
    private final AuthDAO authDAO = new SQLAuthDAO();
    public boolean validateAuth(String authToken) {
        return authDAO.getAuth(authToken);
    }
    public LogoutResponse logout(LogoutRequest logoutRequest, String authToken) {
        if (!validateAuth(authToken)) {
            return new LogoutResponse("Error: unauthorized");
        }
        authDAO.deleteAuth(authToken);

        return new LogoutResponse(null);
    }
}