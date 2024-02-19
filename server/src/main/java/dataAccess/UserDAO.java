package dataAccess;

import model.UserData;

public interface UserDAO {
    void clearUser();

    void createUser(UserData user);

    UserData getUser(String username);
}
