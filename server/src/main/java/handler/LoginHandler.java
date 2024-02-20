package handler;

import com.google.gson.Gson;
import request.LoginRequest;
import response.LoginResponse;
import service.LoginService;

public class LoginHandler {
    private final Gson gson = new Gson();
    private final LoginService loginService = new LoginService();

    public String handleLogin(String jsonLoginRequest) {
        LoginRequest loginRequest = gson.fromJson(jsonLoginRequest, LoginRequest.class);

        LoginResponse loginResponse = loginService.login(loginRequest);
        return gson.toJson(loginResponse);
    }
}
