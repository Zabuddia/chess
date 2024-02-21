package serviceTests;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.RegisterResponse;
import service.RegisterService;

public class RegisterServiceTests {

    @Test
    @DisplayName("Register a user")
    public void registerTest() {
        MemoryAuthDAO.authList.clear();
        MemoryUserDAO.userList.clear();
        MemoryGameDAO.gameList.clear();

        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        UserData user = new UserData(username, password, email);

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(registerRequest);
        String authToken = response.authToken();
        String error = response.message();

        AuthData auth = new AuthData(authToken, username);

        Assertions.assertTrue(MemoryUserDAO.userList.contains(user), "New UserData was not created");
        Assertions.assertTrue(MemoryAuthDAO.authList.contains(auth),"New AuthData was not created");
        Assertions.assertNotEquals("Error: already taken", error, "Says username already exits when it doesn't");
    }

    @Test
    @DisplayName("User already exists")
    public void userAlreadyExistsTest() {
        MemoryAuthDAO.authList.clear();
        MemoryUserDAO.userList.clear();
        MemoryGameDAO.gameList.clear();

        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        UserData user = new UserData(username, password, email);
        MemoryUserDAO.userList.add(user);

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        String error = registerService.register(registerRequest).message();

        Assertions.assertEquals(MemoryUserDAO.userList.size(), 1, "User was added to userList");
        Assertions.assertTrue(MemoryAuthDAO.authList.isEmpty(), "Auth was added to authList");
        Assertions.assertEquals("Error: already taken", error, "Authtoken was created");
    }
}
