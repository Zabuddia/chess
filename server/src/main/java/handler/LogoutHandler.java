package handler;

import com.google.gson.Gson;
import request.LogoutRequest;
import response.LogoutResponse;
import service.LogoutService;
import spark.Request;
import spark.Response;

public class LogoutHandler {
    private final Gson gson = new Gson();
    private final LogoutService logoutService = new LogoutService();

    public String handleLogout(Request request, Response response) {
        LogoutRequest logoutRequest = gson.fromJson(request.body(), LogoutRequest.class);

        LogoutResponse logoutResponse = logoutService.logout(logoutRequest);
        return gson.toJson(logoutResponse);
    }
}
