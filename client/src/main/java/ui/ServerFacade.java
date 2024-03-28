package ui;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import model.GameData;
import request.*;
import response.*;
import webSocketMessages.userCommands.*;

import java.util.ArrayList;
import java.util.Objects;

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
        if (Objects.equals(loginResponse.message(), "Error: unauthorized")) {
            return "Error: unauthorized";
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
        if (Objects.equals(registerResponse.message(), "Error: already taken")) {
            return "Error: already taken";
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
    public void joinObserver(int gameID, String authToken) {
        var path = "/game";
        JoinGameRequest joinGameRequest = new JoinGameRequest(null, gameID);
        httpCommunicator.makeRequest("PUT", path, joinGameRequest, JoinGameResponse.class, authToken);

        JoinObserverCommand joinObserverCommand = new JoinObserverCommand(authToken, gameID);
        try {
            webSocketCommunicator.send(joinObserverCommand);
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
    public void makeMove(int gameID, String authToken, ChessGame.TeamColor teamColor, String move) {
        char firstLetter = move.charAt(0);
        char secondLetter = move.charAt(1);
        char thirdLetter = move.charAt(2);
        char fourthLetter = move.charAt(3);
        switch (firstLetter) {
            case 'a' -> firstLetter = '8';
            case 'b' -> firstLetter = '7';
            case 'c' -> firstLetter = '6';
            case 'd' -> firstLetter = '5';
            case 'e' -> firstLetter = '4';
            case 'f' -> firstLetter = '3';
            case 'g' -> firstLetter = '2';
            case 'h' -> firstLetter = '1';
        }
        switch (thirdLetter) {
            case 'a' -> thirdLetter = '8';
            case 'b' -> thirdLetter = '7';
            case 'c' -> thirdLetter = '6';
            case 'd' -> thirdLetter = '5';
            case 'e' -> thirdLetter = '4';
            case 'f' -> thirdLetter = '3';
            case 'g' -> thirdLetter = '2';
            case 'h' -> thirdLetter = '1';
        }
        int firstNumber = Character.getNumericValue(firstLetter);
        int secondNumber = Character.getNumericValue(secondLetter);
        int thirdNumber = Character.getNumericValue(thirdLetter);
        int fourthNumber = Character.getNumericValue(fourthLetter);

        ChessPosition start = new ChessPosition(secondNumber, firstNumber);
        ChessPosition end = new ChessPosition(fourthNumber, thirdNumber);
        ChessMove chessMove = new ChessMove(start, end, null);

        MakeMoveCommand makeMoveCommand = new MakeMoveCommand(authToken, teamColor, gameID, chessMove);

        try {
            webSocketCommunicator.send(makeMoveCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void redrawChessBoard(int gameID, String authToken) {
        RedrawBoardCommand redrawBoardCommand = new RedrawBoardCommand(authToken, gameID);
        try {
            webSocketCommunicator.send(redrawBoardCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void leave(int gameID, String authToken) {
        LeaveCommand leaveCommand = new LeaveCommand(authToken, gameID);
        try {
            webSocketCommunicator.send(leaveCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void resign(int gameID, String authToken) {
        ResignCommand resignCommand = new ResignCommand(authToken, gameID);
        try {
            webSocketCommunicator.send(resignCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void highlightLegalMoves(int gameID, String authToken, String position) {
        char firstLetter = position.charAt(0);
        char secondLetter = position.charAt(1);
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

        int firstNumber = Character.getNumericValue(firstLetter);
        int secondNumber = Character.getNumericValue(secondLetter);

        ChessPosition chessPosition = new ChessPosition(secondNumber, firstNumber);
        HighlightMovesCommand highlightMovesCommand = new HighlightMovesCommand(authToken, gameID, chessPosition);
        try {
            webSocketCommunicator.send(highlightMovesCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}