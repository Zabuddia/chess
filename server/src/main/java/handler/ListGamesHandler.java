package handler;

import com.google.gson.Gson;
import request.ListGamesRequest;
import response.ListGamesResponse;
import service.ListGamesService;
import spark.Request;
import spark.Response;

public class ListGamesHandler {
    private final Gson gson = new Gson();
    private final ListGamesService listGamesService = new ListGamesService();

    public String handleListGames(Request request, Response response) {
        ListGamesRequest listGamesRequest = gson.fromJson(request.body(), ListGamesRequest.class);

        ListGamesResponse listGamesResponse = listGamesService.listGames(listGamesRequest);
        return gson.toJson(listGamesResponse);
    }
}
