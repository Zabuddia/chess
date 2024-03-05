package dataAccess;

import model.AuthData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {
    public static Collection<AuthData> authList = new ArrayList<>();
    public void clearAuth() {
        authList.clear();
    }
    public String createAuth(String username) {
        String authToken = UUID.randomUUID().toString();
        AuthData auth = new AuthData(authToken, username);
        authList.add(auth);
        return authToken;
    }
    public boolean getAuth(String authToken) {
        for (AuthData auth : authList) {
            if (Objects.equals(auth.authToken(), authToken)) {
                return true;
            }
        }
        return false;
    }
    public void deleteAuth(String authToken) {
        authList.removeIf(auth -> Objects.equals(auth.authToken(), authToken));
    }
    public String getUsername(String authToken) {
        for (AuthData auth : authList) {
            if (Objects.equals(auth.authToken(), authToken)) {
                return auth.username();
            }
        }
        return null;
    }
}
