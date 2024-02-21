package handler;

import com.google.gson.Gson;
import request.LoginRequest;
import response.LoginResponse;
import service.LoginService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class LoginHandler {
    private final Gson gson = new Gson();
    private final LoginService loginService = new LoginService();

    public String handleLogin(Request request, Response response) {
        LoginRequest loginRequest = gson.fromJson(request.body(), LoginRequest.class);

        LoginResponse loginResponse = loginService.login(loginRequest);
        if (Objects.equals(loginResponse.message(), "Error: unauthorized")) {
            response.status(401);
        } else {
            response.status(200);
        }

        return gson.toJson(loginResponse);
    }
}
