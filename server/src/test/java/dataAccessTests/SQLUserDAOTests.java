package dataAccessTests;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import service.RegisterService;

public class SQLUserDAOTests {
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
    @DisplayName("Clear User Test")
    public void clearAuthTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);
        userDAO.clearUser();
        Assertions.assertTrue(SQLDAO.isEmpty("user"), "user was not cleared");
    }
    @Test
    @DisplayName("Create User Positive Test")
    public void createUserPositiveTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        userDAO.createUser(username, password, email);
        Assertions.assertFalse(SQLDAO.isEmpty("user"), "user was not created");
    }
    @Test
    @DisplayName("Create User Negative Test")
    public void createUserNegativeTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        String username2 = "buddia";
        String password2 = "54321";
        String email2 = "fife.alan@gmail.com";
        userDAO.createUser(username2, password2, email2);

        Assertions.assertEquals(1, SQLDAO.tableSize("user"), "user was created");
    }
    @Test
    @DisplayName("Get User Positive Test")
    public void getUserPositiveTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        Assertions.assertNotNull(userDAO.getUser(username), "user was not found");
    }
    @Test
    @DisplayName("Get User Negative Test")
    public void getUserNegativeTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        String username2 = "buddia2";
        Assertions.assertNull(userDAO.getUser(username2), "user was found");
    }
    @Test
    @DisplayName("Verify User Positive Test")
    public void verifyUserPositiveTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        Assertions.assertTrue(userDAO.verifyUser(username, password), "user was not verified");
    }
    @Test
    @DisplayName("Verify User Negative Test")
    public void verifyUserNegativeTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterService registerService = new RegisterService();
        registerService.register(registerRequest);

        String username2 = "buddia2";
        String password2 = "54321";
        Assertions.assertFalse(userDAO.verifyUser(username2, password2), "user was verified");
    }
}
