package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import response.JoinGameResponse;
import service.JoinGameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {
    private final Gson gson = new Gson();
    private final JoinGameService joinGameService = new JoinGameService();

    public String handleJoinGame(Request request, Response response) {
        JoinGameRequest joinGameRequest = gson.fromJson(request.body(), JoinGameRequest.class);

        JoinGameResponse joinGameResponse = joinGameService.joinGame(joinGameRequest);
        return gson.toJson(joinGameResponse);
    }
}
