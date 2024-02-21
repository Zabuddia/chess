package handler;

import com.google.gson.Gson;
import request.ListGamesRequest;
import response.ListGamesResponse;
import service.ListGamesService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class ListGamesHandler {
    private final Gson gson = new Gson();
    private final ListGamesService listGamesService = new ListGamesService();

    public String handleListGames(Request request, Response response) {
        String authToken = request.headers("Authorization");

        ListGamesRequest listGamesRequest = gson.fromJson(request.body(), ListGamesRequest.class);

        ListGamesResponse listGamesResponse = listGamesService.listGames(listGamesRequest, authToken);
        if (Objects.equals(listGamesResponse.message(), "Error: unauthorized")) {
            response.status(401);
        } else {
            response.status(200);
        }

        return gson.toJson(listGamesResponse);
    }
}
