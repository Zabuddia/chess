package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import response.JoinGameResponse;
import service.JoinGameService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class JoinGameHandler {
    private final Gson gson = new Gson();
    private final JoinGameService joinGameService = new JoinGameService();

    public String handleJoinGame(Request request, Response response) {
        String authToken = request.headers("Authorization");

        JoinGameRequest joinGameRequest = gson.fromJson(request.body(), JoinGameRequest.class);

        JoinGameResponse joinGameResponse = joinGameService.joinGame(joinGameRequest, authToken);
        if (Objects.equals(joinGameResponse.message(), "Error: bad request")) {
            response.status(400);
        } else if(Objects.equals(joinGameResponse.message(), "Error: unauthorized")) {
            response.status(401);
        } else if (Objects.equals(joinGameResponse.message(), "Error: already taken")) {
            response.status(403);
        } else {
            response.status(200);
        }

        return gson.toJson(joinGameResponse);
    }
}
