package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import response.JoinGameResponse;
import service.JoinGameService;

public class JoinGameHandler {
    private final Gson gson = new Gson();
    private final JoinGameService joinGameService = new JoinGameService();

    public String handleJoinGame(String jsonJoinGameRequest) {
        JoinGameRequest joinGameRequest = gson.fromJson(jsonJoinGameRequest, JoinGameRequest.class);

        JoinGameResponse joinGameResponse = joinGameService.joinGame(joinGameRequest);
        return gson.toJson(joinGameResponse);
    }
}
