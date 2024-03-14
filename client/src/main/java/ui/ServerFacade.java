package ui;

import model.GameData;

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
}
