package serviceTests;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.LogoutRequest;
import request.RegisterRequest;
import response.RegisterResponse;
import service.LogoutService;
import service.RegisterService;

public class LogoutServiceTests {
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
    @DisplayName("Logout")
    public void logoutTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);

        LogoutRequest logoutRequest = new LogoutRequest(true);
        LogoutService logoutService = new LogoutService();

        logoutService.logout(logoutRequest, registerResponse.authToken());

        Assertions.assertTrue(SQLDAO.isEmpty("auth"), "Did not clear authToken");
    }

    @Test
    @DisplayName("Unauthorized logout")
    public void unauthorizedLogoutTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        LogoutRequest logoutRequest = new LogoutRequest(true);
        LogoutService logoutService = new LogoutService();

        logoutService.logout(logoutRequest, "unauthorized");

        Assertions.assertFalse(SQLDAO.isEmpty("auth"), "Cleared the authToken when unauthorized to do so");
    }
}