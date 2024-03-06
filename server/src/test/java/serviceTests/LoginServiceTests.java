package serviceTests;

import dataAccess.*;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import response.RegisterResponse;
import service.LoginService;
import service.RegisterService;

public class LoginServiceTests {
    AuthDAO authDAO = new SQLAuthDAO();
    UserDAO userDAO = new SQLUserDAO();
    GameDAO gameDAO = new SQLGameDAO();
    @BeforeEach
    public void clearAll() {
        authDAO.clearAuth();
        userDAO.clearUser();
        gameDAO.clearGame();
    }
    @Test
    @DisplayName("Login")
    public void loginTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginService loginService = new LoginService();
        String error = loginService.login(loginRequest).message();

        Assertions.assertFalse(SQLDAO.isEmpty("auth"), "AuthToken was not created");
        Assertions.assertNotEquals("Error: unauthorized", error, "Says incorrect password when password is correct");
    }

    @Test
    @DisplayName("Incorrect password")
    public void incorrectPasswordTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        String incorrectPassword = "54321";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest(username, incorrectPassword);
        LoginService loginService = new LoginService();
        String error = loginService.login(loginRequest).message();

        Assertions.assertFalse(SQLDAO.isEmpty("auth"), "AuthToken was created when it should not have been");
        Assertions.assertEquals("Error: unauthorized", error, "Says correct password when password is incorrect");
    }
}
