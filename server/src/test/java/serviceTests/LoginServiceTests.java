package serviceTests;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import service.LoginService;

public class LoginServiceTests {
    @BeforeEach
    public void clearAll() {
        MemoryAuthDAO.authList.clear();
        MemoryUserDAO.userList.clear();
        MemoryGameDAO.gameList.clear();
    }
    @Test
    @DisplayName("Login")
    public void loginTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";

        UserData user = new UserData(username, password, email);
        MemoryUserDAO.userList.add(user);

        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginService loginService = new LoginService();
        String error = loginService.login(loginRequest).message();

        Assertions.assertEquals(MemoryAuthDAO.authList.size(), 1, "AuthToken was not created");
        Assertions.assertNotEquals("Error: unauthorized", error, "Says incorrect password when password is correct");
    }

    @Test
    @DisplayName("Incorrect password")
    public void incorrectPasswordTest() {
        String username = "buddia";
        String password = "12345";
        String email = "fife.alan@gmail.com";
        String incorrectPassword = "54321";

        UserData user = new UserData(username, password, email);
        MemoryUserDAO.userList.add(user);

        LoginRequest loginRequest = new LoginRequest(username, incorrectPassword);
        LoginService loginService = new LoginService();
        String error = loginService.login(loginRequest).message();

        Assertions.assertEquals(MemoryAuthDAO.authList.size(), 0, "AuthToken was created when it should not have been");
        Assertions.assertEquals("Error: unauthorized", error, "Says correct password when password is incorrect");
    }
}
