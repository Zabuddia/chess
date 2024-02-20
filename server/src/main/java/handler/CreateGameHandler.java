package handler;

import com.google.gson.Gson;
import request.CreateGameRequest;
import response.CreateGameResponse;
import service.CreateGameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler {
    private final Gson gson = new Gson();
    private final CreateGameService createGameService = new CreateGameService();

    public String handleCreateGame(Request request, Response response) {
        CreateGameRequest createGameRequest = gson.fromJson(request.body(), CreateGameRequest.class);

        CreateGameResponse createGameResponse = createGameService.createGame(createGameRequest);
        return gson.toJson(createGameResponse);
    }
}
