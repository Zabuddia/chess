package dataAccessTests;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.RegisterResponse;
import service.RegisterService;

public class SQLAuthDAOTests {
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
    @DisplayName("Clear Auth Test")
    public void clearAuthTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);
        authDAO.clearAuth();
        Assertions.assertTrue(SQLDAO.isEmpty("auth"), "auth was not cleared");
    }
    @Test
    @DisplayName("Create Auth Test")
    public void createAuthPositiveTest() {
        String username = "buddia";
        authDAO.createAuth(username);
        Assertions.assertFalse(SQLDAO.isEmpty("auth"), "auth was not created");
    }
    @Test
    @DisplayName("Get Auth Positive Test")
    public void getAuthPositiveTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);
        String authToken = registerResponse.authToken();
        Assertions.assertTrue(authDAO.getAuth(authToken), "auth was not retrieved");
    }
    @Test
    @DisplayName("Get Auth Negative Test")
    public void getAuthNegativeTest() {
        String username = "buddia";
        Assertions.assertFalse(authDAO.getAuth(username), "auth was retrieved");
    }
    @Test
    @DisplayName("Delete Auth Positive Test")
    public void deleteAuthPositiveTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);
        String authToken = registerResponse.authToken();
        authDAO.deleteAuth(authToken);
        Assertions.assertTrue(SQLDAO.isEmpty("auth"), "auth was not deleted");
    }
    @Test
    @DisplayName("Delete Auth Negative Test")
    public void deleteAuthNegativeTest() {
        String username = "buddia";
        authDAO.createAuth(username);
        authDAO.deleteAuth("notAuth");
        Assertions.assertFalse(SQLDAO.isEmpty("auth"), "auth was deleted");
    }
    @Test
    @DisplayName("Get Username Positive Test")
    public void getUsernamePositiveTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        RegisterResponse registerResponse = registerService.register(registerRequest);
        String authToken = registerResponse.authToken();
        Assertions.assertEquals(username, authDAO.getUsername(authToken), "username was not retrieved");
    }
    @Test
    @DisplayName("Get Username Negative Test")
    public void getUsernameNegativeTest() {
        String username = "buddia";
        Assertions.assertNull(authDAO.getUsername(username), "username was retrieved");
    }
}