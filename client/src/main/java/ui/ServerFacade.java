package ui;

import model.GameData;
import request.CreateGameRequest;
import request.ListGamesRequest;
import request.LoginRequest;
import request.RegisterRequest;
import response.CreateGameResponse;
import response.ListGamesResponse;
import response.LoginResponse;
import response.RegisterResponse;

import java.util.ArrayList;

public class ServerFacade {
    private final ClientCommunicator clientCommunicator = new ClientCommunicator();
    public ArrayList<GameData> listGames(String authToken) {
        var path = "/game";
        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesResponse listGamesResponse = clientCommunicator.makeRequest("GET", path, listGamesRequest, ListGamesResponse.class, authToken);
        return listGamesResponse.games();
    }
    public String login(String username, String password) {
        var path = "/session";
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginResponse loginResponse = clientCommunicator.makeRequest("POST", path, loginRequest, LoginResponse.class, null);
        return loginResponse.authToken();
    }
    public String register(String username, String password, String email) {
        var path = "/user";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterResponse registerResponse = clientCommunicator.makeRequest("POST", path, registerRequest, RegisterResponse.class, null);
        return registerResponse.authToken();
    }
    public void createGame(String gameName, String authToken) {
        var path = "/game";
        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        clientCommunicator.makeRequest("POST", path, createGameRequest, CreateGameResponse.class, authToken);
    }
}
