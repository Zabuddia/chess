package dataAccess;

import model.UserData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MemoryUserDAO implements UserDAO {
    public static Collection<UserData> userList = new ArrayList<>();
    @Override
    public void clearUser() {
        userList.clear();
    }

    @Override
    public void createUser(UserData user) {
        userList.add(user);
    }

    @Override
    public UserData getUser(String username) {
        for (UserData user : userList) {
            if (Objects.equals(user.username(), username)) {
                return user;
            }
        }
        return null;
    }
}
