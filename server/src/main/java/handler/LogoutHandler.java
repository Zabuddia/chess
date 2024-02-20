package handler;

import com.google.gson.Gson;
import request.LogoutRequest;
import response.LogoutResponse;
import service.LogoutService;

public class LogoutHandler {
    private final Gson gson = new Gson();
    private final LogoutService logoutService = new LogoutService();

    public String handleLogout(String jsonLogoutRequest) {
        LogoutRequest logoutRequest = gson.fromJson(jsonLogoutRequest, LogoutRequest.class);

        LogoutResponse logoutResponse = logoutService.logout(logoutRequest);
        return gson.toJson(logoutResponse);
    }
}
