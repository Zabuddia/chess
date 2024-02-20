package serviceTests;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import response.RegisterResponse;
import service.RegisterService;

public class RegisterServiceTests {

    @Test
    @DisplayName("Register a user")
    public void registerTest() {
        MemoryUserDAO.userList.clear();
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        UserData user = new UserData(username, password, email);

        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(username, password, email);
        String authToken = response.authToken();
        String error = response.error();

        AuthData auth = new AuthData(authToken, username);

        Assertions.assertTrue(MemoryUserDAO.userList.contains(user), "New UserData was not created");
        Assertions.assertTrue(MemoryAuthDAO.authList.contains(auth),"New AuthData was not created");
        Assertions.assertNotEquals("Error: already taken", error, "Says username already exits when it doesn't");
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
        String error = registerService.register(username, password, email).error();

        Assertions.assertEquals(MemoryUserDAO.userList.size(), 1, "User was added to userList");
        Assertions.assertTrue(MemoryAuthDAO.authList.isEmpty(), "Auth was added to authList");
        Assertions.assertEquals("Error: already taken", error, "Authtoken was created");
    }
}
