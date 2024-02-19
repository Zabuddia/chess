package service;

import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;

public class RegisterService {
    public void register(String username, String password, String email) {
        UserDAO userDAO = new MemoryUserDAO();
    }
}
