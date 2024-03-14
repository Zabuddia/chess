package ui;

import model.GameData;
import request.CreateGameRequest;
import request.LoginRequest;
import request.RegisterRequest;

import java.util.ArrayList;

public class ServerFacade {
    private final String serverUrl;
    private final ClientCommunicator clientCommunicator = new ClientCommunicator();
    public ServerFacade(String url) {
        serverUrl = url;
    }
    public ArrayList<GameData> listGames() {
        return clientCommunicator.get(serverUrl + "/game");
    }
    public String login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        return clientCommunicator.post(serverUrl + "/session", loginRequest);
    }
    public String register(String username, String password, String email) {
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        return clientCommunicator.post(serverUrl + "/user", registerRequest);
    }
    public void createGame(String gameName) {
        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        clientCommunicator.post(serverUrl + "/game", createGameRequest);
    }
}
