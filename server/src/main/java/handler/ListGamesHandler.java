package handler;

import com.google.gson.Gson;
import request.ListGamesRequest;
import response.ListGamesResponse;
import service.ListGamesService;

public class ListGamesHandler {
    private final Gson gson = new Gson();
    private final ListGamesService listGamesService = new ListGamesService();

    public String handleListGames(String jsonListGamesRequest) {
        ListGamesRequest listGamesRequest = gson.fromJson(jsonListGamesRequest, ListGamesRequest.class);

        ListGamesResponse listGamesResponse = listGamesService.listGames(listGamesRequest);
        return gson.toJson(listGamesResponse);
    }
}
