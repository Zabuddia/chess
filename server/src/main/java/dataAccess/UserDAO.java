package dataAccess;

import model.UserData;

public interface UserDAO {
    void clearUser();

    void createUser(String username, String password, String email);

    UserData getUser(String username);

    boolean verifyUser(String username, String password);
}