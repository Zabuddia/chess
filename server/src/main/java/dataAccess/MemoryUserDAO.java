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
    public void createUser(String username, String password, String email) {
        UserData user = new UserData(username, password, email);
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

    @Override
    public boolean verifyUser(String username, String password) {
        UserData userToVerify = null;
        for (UserData user : userList) {
            if (Objects.equals(user.username(), username)) {
                userToVerify = user;
            }
        }
        if (userToVerify == null) {
            return false;
        }
        if (Objects.equals(userToVerify.password(), password)) {
            return true;
        } else {
            return false;
        }
    }
}
