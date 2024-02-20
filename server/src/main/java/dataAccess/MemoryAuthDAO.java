package dataAccess;

import model.AuthData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {
    public static Collection<AuthData> authList = new ArrayList<>();
    @Override
    public void clearAuth() {
        authList.clear();
    }

    @Override
    public String createAuth(String username) {
        String authToken = UUID.randomUUID().toString();
        AuthData auth = new AuthData(authToken, username);
        authList.add(auth);
        return authToken;
    }

    @Override
    public boolean getAuth(String authToken) {
        for (AuthData auth : authList) {
            if (Objects.equals(auth.authToken(), authToken)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteAuth(String authToken) {
        authList.removeIf(auth -> Objects.equals(auth.authToken(), authToken));
    }

    @Override
    public String getUsername(String authToken) {
        for (AuthData auth : authList) {
            if (Objects.equals(auth.authToken(), authToken)) {
                return auth.username();
            }
        }
        return null;
    }
}
