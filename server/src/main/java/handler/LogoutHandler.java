package handler;

import com.google.gson.Gson;
import request.LogoutRequest;
import response.LogoutResponse;
import service.LogoutService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class LogoutHandler {
    private final Gson gson = new Gson();
    private final LogoutService logoutService = new LogoutService();

    public String handleLogout(Request request, Response response) {
        String authToken = request.headers("Authorization");
        LogoutRequest logoutRequest = gson.fromJson(request.body(), LogoutRequest.class);
        LogoutResponse logoutResponse = logoutService.logout(logoutRequest, authToken);
        if (Objects.equals(logoutResponse.message(), "Error: unauthorized")) {
            response.status(401);
        } else {
            response.status(200);
        }
        return gson.toJson(logoutResponse);
    }
}