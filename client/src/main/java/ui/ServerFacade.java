package ui;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import model.GameData;
import request.*;
import response.*;
import webSocketMessages.userCommands.JoinPlayerCommand;
import webSocketMessages.userCommands.MakeMoveCommand;
import webSocketMessages.userCommands.RedrawBoardCommand;

import java.util.ArrayList;

public class ServerFacade {
    private final HttpCommunicator httpCommunicator;
    private WebSocketCommunicator webSocketCommunicator;
    public ServerFacade(int port) {
        httpCommunicator = new HttpCommunicator("http://localhost:" + port);
        try {
            webSocketCommunicator = new WebSocketCommunicator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<GameData> listGames(String authToken) {
        var path = "/game";
        ListGamesRequest listGamesRequest = new ListGamesRequest(true);
        ListGamesResponse listGamesResponse = httpCommunicator.makeRequest("GET", path, listGamesRequest, ListGamesResponse.class, authToken);
        if (listGamesResponse == null) {
            return null;
        }
        return listGamesResponse.games();
    }
    public String login(String username, String password) {
        var path = "/session";
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginResponse loginResponse = httpCommunicator.makeRequest("POST", path, loginRequest, LoginResponse.class, null);
        if (loginResponse == null) {
            return null;
        }
        return loginResponse.authToken();
    }
    public String register(String username, String password, String email) {
        var path = "/user";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterResponse registerResponse = httpCommunicator.makeRequest("POST", path, registerRequest, RegisterResponse.class, null);
        if (registerResponse == null) {
            return null;
        }
        return registerResponse.authToken();
    }
    public int createGame(String gameName, String authToken) {
        var path = "/game";
        CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
        CreateGameResponse createGameResponse = httpCommunicator.makeRequest("POST", path, createGameRequest, CreateGameResponse.class, authToken);
        if (createGameResponse == null) {
            return -1;
        }
        return createGameResponse.gameID();
    }
    public void joinGame(ChessGame.TeamColor color, int gameID, String authToken) {
        var path = "/game";
        JoinGameRequest joinGameRequest = new JoinGameRequest(color, gameID);
        httpCommunicator.makeRequest("PUT", path, joinGameRequest, JoinGameResponse.class, authToken);

        JoinPlayerCommand joinPlayerCommand = new JoinPlayerCommand(authToken, gameID, color);
        try {
            webSocketCommunicator.send(joinPlayerCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void logout(String authToken) {
        var path = "/session";
        LogoutRequest logoutRequest = new LogoutRequest(true);
        httpCommunicator.makeRequest("DELETE", path, logoutRequest, null, authToken);
    }
    public void clear() {
        var path = "/db";
        ClearRequest clearRequest = new ClearRequest(true);
        httpCommunicator.makeRequest("DELETE", path, clearRequest, ClearResponse.class, null);
    }
    public void makeMove(int gameID, String authToken, String move) {
        char firstLetter = move.charAt(0);
        char secondLetter = move.charAt(1);
        char thirdLetter = move.charAt(2);
        char fourthLetter = move.charAt(3);
        switch (firstLetter) {
            case 'a' -> firstLetter = '1';
            case 'b' -> firstLetter = '2';
            case 'c' -> firstLetter = '3';
            case 'd' -> firstLetter = '4';
            case 'e' -> firstLetter = '5';
            case 'f' -> firstLetter = '6';
            case 'g' -> firstLetter = '7';
            case 'h' -> firstLetter = '8';
        }
        switch (thirdLetter) {
            case 'a' -> thirdLetter = '1';
            case 'b' -> thirdLetter = '2';
            case 'c' -> thirdLetter = '3';
            case 'd' -> thirdLetter = '4';
            case 'e' -> thirdLetter = '5';
            case 'f' -> thirdLetter = '6';
            case 'g' -> thirdLetter = '7';
            case 'h' -> thirdLetter = '8';
        }
        int firstNumber = Character.getNumericValue(firstLetter) - 1;
        int secondNumber = Character.getNumericValue(secondLetter) - 1;
        int thirdNumber = Character.getNumericValue(thirdLetter) - 1;
        int fourthNumber = Character.getNumericValue(fourthLetter) - 1;

        ChessPosition start = new ChessPosition(firstNumber, secondNumber);
        ChessPosition end = new ChessPosition(thirdNumber, fourthNumber);
        ChessMove chessMove = new ChessMove(start, end, null);

        MakeMoveCommand makeMoveCommand = new MakeMoveCommand(authToken, gameID, chessMove);

        try {
            webSocketCommunicator.send(makeMoveCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void redrawChessBoard(int gameID, String authToken) {
        var path = "/game";
        RedrawBoardCommand redrawBoardCommand = new RedrawBoardCommand(authToken, gameID);
        try {
            webSocketCommunicator.send(redrawBoardCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}