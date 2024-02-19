package dataAccess;

import model.AuthData;

import java.util.ArrayList;
import java.util.Collection;
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
}
