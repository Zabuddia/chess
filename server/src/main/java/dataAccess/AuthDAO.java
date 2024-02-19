package dataAccess;

import model.AuthData;

public interface AuthDAO {
    void clearAuth();

    void createAuth(AuthData auth);
}
