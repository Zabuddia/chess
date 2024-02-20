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
        String authToken = request.headers("Authorization");

        JoinGameRequest joinGameRequest = gson.fromJson(request.body(), JoinGameRequest.class);

        JoinGameResponse joinGameResponse = joinGameService.joinGame(joinGameRequest, authToken);

        return gson.toJson(joinGameResponse);
    }
}
