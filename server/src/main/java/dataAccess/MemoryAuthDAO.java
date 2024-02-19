package dataAccess;

import model.AuthData;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryAuthDAO implements AuthDAO {
    public static Collection<AuthData> authList = new ArrayList<>();
    @Override
    public void clearAuth() {
        authList.clear();
    }

    @Override
    public void createAuth(AuthData auth) {
        authList.add(auth);
    }
}
