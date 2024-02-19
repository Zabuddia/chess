package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;

public class LogoutService {
    public void logout(String authToken) {
        AuthDAO authDAO = new MemoryAuthDAO();

        if (!authDAO.getAuth(authToken)) {
            return;
        }

        authDAO.deleteAuth(authToken);
    }
}
