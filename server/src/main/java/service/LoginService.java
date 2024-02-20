package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;
import request.LoginRequest;
import response.LoginResponse;

public class LoginService {
    private final UserDAO userDAO = new MemoryUserDAO();
    private final AuthDAO authDAO = new MemoryAuthDAO();
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        if (!userDAO.verifyUser(username, password)) {
            return new LoginResponse(401, "message", "Error: unauthorized", null, null);
        }

        return new LoginResponse(200, null, null, username, authDAO.createAuth(username));
    }
}
