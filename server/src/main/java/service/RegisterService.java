package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;
import model.UserData;

public class RegisterService {
    public String register(String username, String password, String email) {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        UserData user = userDAO.getUser(username);
        if (user != null) {
            return "Username already exists";
        }
        userDAO.createUser(username, password, email);
        return authDAO.createAuth(username);
    }
}
