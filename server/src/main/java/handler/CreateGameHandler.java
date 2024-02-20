package handler;

import com.google.gson.Gson;
import request.CreateGameRequest;
import response.CreateGameResponse;
import service.CreateGameService;

public class CreateGameHandler {
    private final Gson gson = new Gson();
    private final CreateGameService createGameService = new CreateGameService();

    public String handleCreateGame(String jsonCreateGameRequest) {
        CreateGameRequest createGameRequest = gson.fromJson(jsonCreateGameRequest, CreateGameRequest.class);

        CreateGameResponse createGameResponse = createGameService.createGame(createGameRequest);
        return gson.toJson(createGameResponse);
    }
}
