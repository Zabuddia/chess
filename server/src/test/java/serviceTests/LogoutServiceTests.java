package serviceTests;

import dataAccess.MemoryAuthDAO;
import model.AuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.LogoutRequest;
import service.LogoutService;

public class LogoutServiceTests {

    @Test
    @DisplayName("Logout")
    public void logoutTest() {
        String username = "buddia";
        String authToken = "12345";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        LogoutRequest logoutRequest = new LogoutRequest(true);
        LogoutService logoutService = new LogoutService();

        logoutService.logout(logoutRequest, authToken);

        Assertions.assertTrue(MemoryAuthDAO.authList.isEmpty(), "Did not clear authToken");
    }

    @Test
    @DisplayName("Unauthorized logout")
    public void unauthorizedLogoutTest() {
        String username = "buddia";
        String authToken = "12345";
        String unauthorizedAuthToken = "54321";

        AuthData auth = new AuthData(authToken, username);
        MemoryAuthDAO.authList.add(auth);

        LogoutRequest logoutRequest = new LogoutRequest(true);
        LogoutService logoutService = new LogoutService();

        logoutService.logout(logoutRequest, unauthorizedAuthToken);

        Assertions.assertEquals(MemoryAuthDAO.authList.size(), 1, "Cleared the authToken when unauthorized to do so");
    }
}
