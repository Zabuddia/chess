package handler;

import com.google.gson.Gson;
import request.RegisterRequest;
import response.RegisterResponse;
import service.RegisterService;

public class RegisterHandler {
    private final Gson gson = new Gson();
    private final RegisterService registerService = new RegisterService();

    public String handleRegister(String jsonRegisterRequest) {
        RegisterRequest registerRequest = gson.fromJson(jsonRegisterRequest, RegisterRequest.class);

        RegisterResponse registerResponse = registerService.register(registerRequest);
        return gson.toJson(registerResponse);
    }
}
