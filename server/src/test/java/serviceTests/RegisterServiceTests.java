package serviceTests;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RegisterService;

import java.util.Objects;

public class RegisterServiceTests {

    @Test
    @DisplayName("Register a user")
    public void registerTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        UserData user = new UserData(username, password, email);

        RegisterService registerService = new RegisterService();
        String authToken = registerService.register(username, password, email);

        AuthData auth = new AuthData(authToken, username);

        Assertions.assertTrue(MemoryUserDAO.userList.contains(user), "New UserData was not created");
        Assertions.assertTrue(MemoryAuthDAO.authList.contains(auth),"New AuthData was not created");
        Assertions.assertNotEquals("Username already exists", authToken, "Says username already exits when it doesn't");
    }

    @Test
    @DisplayName("User already exists")
    public void userAlreadyExistsTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        UserData user = new UserData(username, password, email);
        MemoryUserDAO.userList.add(user);

        RegisterService registerService = new RegisterService();
        String authToken = registerService.register(username, password, email);

        Assertions.assertEquals(MemoryUserDAO.userList.size(), 1, "User was added to userList");
        Assertions.assertTrue(MemoryAuthDAO.authList.isEmpty(), "Auth was added to authList");
        Assertions.assertEquals("Username already exists", authToken, "Authtoken was created");
    }
}
