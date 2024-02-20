package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;
import request.LogoutRequest;
import response.LogoutResponse;

public class LogoutService {
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        String authToken = logoutRequest.authToken();
        AuthDAO authDAO = new MemoryAuthDAO();

        if (!authDAO.getAuth(authToken)) {
            return new LogoutResponse(401, "message", "Error: unauthorized");
        }

        authDAO.deleteAuth(authToken);

        return new LogoutResponse(200, null, null);
    }
}
