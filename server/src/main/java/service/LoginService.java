package service;

import dataAccess.*;
import request.LoginRequest;
import response.LoginResponse;

public class LoginService {
    private final UserDAO userDAO = new SQLUserDAO();
    private final AuthDAO authDAO = new SQLAuthDAO();
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        if (!userDAO.verifyUser(username, password)) {
            return new LoginResponse("Error: unauthorized", null, null);
        }

        return new LoginResponse(null, username, authDAO.createAuth(username));
    }
}
