package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;

public class LoginService {
    public String login(String username, String password) {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();

        if (!userDAO.verifyUser(username, password)) {
            return "Incorrect password";
        }

        return authDAO.createAuth(username);
    }
}
