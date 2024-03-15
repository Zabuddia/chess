package dataAccess;

import model.AuthData;

public interface AuthDAO {
    void clearAuth();

    String createAuth(String username);

    boolean getAuth(String authToken);

    void deleteAuth(String authToken);

    String getUsername(String authToken);
}