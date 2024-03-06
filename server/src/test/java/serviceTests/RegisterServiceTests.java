package serviceTests;

import dataAccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.RegisterResponse;
import service.RegisterService;

public class RegisterServiceTests {
    AuthDAO authDAO = new SQLAuthDAO();
    UserDAO userDAO = new SQLUserDAO();
    GameDAO gameDAO = new SQLGameDAO();
    @BeforeEach
    public void clearAll() {
        authDAO.clearAuth();
        userDAO.clearUser();
        gameDAO.clearGame();
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);
    }
    @Test
    @DisplayName("Register a user")
    public void registerTest() {
        Assertions.assertFalse(SQLDAO.isEmpty("user"), "New UserData was not created");
        Assertions.assertFalse(SQLDAO.isEmpty("auth"),"New AuthData was not created");
    }

    @Test
    @DisplayName("User already exists")
    public void userAlreadyExistsTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        RegisterRequest registerRequest2 = new RegisterRequest(username, password, email);
        RegisterService registerService2 = new RegisterService();
        RegisterResponse registerResponse2 = registerService2.register(registerRequest2);
        String error = registerResponse2.message();

        Assertions.assertEquals("Error: already taken", error, "Authtoken was created");
    }
}
