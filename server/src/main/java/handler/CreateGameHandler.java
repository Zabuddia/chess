package handler;

import com.google.gson.Gson;
import request.CreateGameRequest;
import response.CreateGameResponse;
import service.CreateGameService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class CreateGameHandler {
    private final Gson gson = new Gson();
    private final CreateGameService createGameService = new CreateGameService();

    public String handleCreateGame(Request request, Response response) {
        String authToken = request.headers("Authorization");

        CreateGameRequest createGameRequest = gson.fromJson(request.body(), CreateGameRequest.class);

        CreateGameResponse createGameResponse = createGameService.createGame(authToken, createGameRequest);
        if (Objects.equals(createGameResponse.error(), "Error: unauthorized")) {
            response.status(401);
        } else {
            response.status(200);
        }

        return gson.toJson(createGameResponse);
    }
}
