package dataAccess;

import model.AuthData;

public interface AuthDAO {
    void clearAuth();

    String createAuth(String username);

}
