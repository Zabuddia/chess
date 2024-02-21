package handler;

import com.google.gson.Gson;
import request.RegisterRequest;
import response.RegisterResponse;
import service.RegisterService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class RegisterHandler {
    private final Gson gson = new Gson();
    private final RegisterService registerService = new RegisterService();

    public String handleRegister(Request request, Response response) {
        RegisterRequest registerRequest = gson.fromJson(request.body(), RegisterRequest.class);

        RegisterResponse registerResponse = registerService.register(registerRequest);
        if (Objects.equals(registerResponse.message(), "Error: already taken")) {
            response.status(403);
        } else {
            response.status(200);
        }

        return gson.toJson(registerResponse);
    }
}
