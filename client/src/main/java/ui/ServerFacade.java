package ui;

import chess.ChessGame;
import model.GameData;
import request.*;
import response.*;

import java.util.ArrayList;

public class ServerFacade {
    private final ClientCommunicator clientCommunicator;
    public ServerFacade(int port) {
        clientCommunicator = new ClientCommunicator("http://localhost:" + port);
    }
    public ArrayList<GameData> listGames(String authToken) {
        var path = "/game";
        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesResponse listGamesResponse = clientCommunicator.makeRequest("GET", path, listGamesRequest, ListGamesResponse.class, authToken);
        if (listGamesResponse == null) {
            return null;
        }
        return listGamesResponse.games();
    }
    public String login(String username, String password) {
        var path = "/session";
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginResponse loginResponse = clientCommunicator.makeRequest("POST", path, loginRequest, LoginResponse.class, null);
        if (loginResponse == null) {
            return null;
        }
        return loginResponse.authToken();
    }
    public String register(String username, String password, String email) {
        var path = "/user";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterResponse registerResponse = clientCommunicator.makeRequest("POST", path, registerRequest, RegisterResponse.class, null);
        if (registerResponse == null) {
            return null;
        }
        return registerResponse.authToken();
    }
    public int createGame(String gameName, String authToken) {
        var path = "/game";
        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameResponse createGameResponse = clientCommunicator.makeRequest("POST", path, createGameRequest, CreateGameResponse.class, authToken);
        if (createGameResponse == null) {
            return -1;
        }
        return createGameResponse.gameID();
    }
    public void joinGame(ChessGame.TeamColor color, int gameID, String authToken) {
        var path = "/game";
        JoinGameRequest joinGameRequest = new JoinGameRequest(color, gameID);
        clientCommunicator.makeRequest("PUT", path, joinGameRequest, JoinGameResponse.class, authToken);
    }
    public void logout(String authToken) {
        var path = "/session";
        LogoutRequest logoutRequest = new LogoutRequest(true);
        clientCommunicator.makeRequest("DELETE", path, logoutRequest, null, authToken);
    }
    public void clear() {
        var path = "/db";
        ClearRequest clearRequest = new ClearRequest(true);
        clientCommunicator.makeRequest("DELETE", path, clearRequest, ClearResponse.class, null);
    }
}