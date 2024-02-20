package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;
import model.UserData;
import response.RegisterResponse;

public class RegisterService {
    public RegisterResponse register(String username, String password, String email) {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        UserData user = userDAO.getUser(username);
        if (user != null) {
            return new RegisterResponse(403, "message", "Error: already taken", null, null);
        }
        userDAO.createUser(username, password, email);
        return new RegisterResponse(200, null, null, username, authDAO.createAuth(username));
    }
}
