package server.websocket;

import chess.ChessGame;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataAccess.*;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import webSocketMessages.serverMessages.*;
import webSocketMessages.userCommands.GameCommand;
import webSocketMessages.userCommands.UserGameCommand;
import webSocketMessages.userCommands.UserGameCommandDeserializer;

import java.io.IOException;

@WebSocket
public class WebSocketHandler {
    private final AuthDAO authDAO = new SQLAuthDAO();
    private final SQLGameDAO gameDAO = new SQLGameDAO();
    private final ConnectionManager connectionManager = new ConnectionManager();
    @OnWebSocketMessage
    public void onMessage(Session session, String msg) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UserGameCommand.class, new UserGameCommandDeserializer());
        Gson gson = builder.create();

        GameCommand command = gson.fromJson(msg, UserGameCommand.class);

        try {
            switch (command.getCommandType()) {
                case JOIN_PLAYER -> join(command, session);
                case JOIN_OBSERVER -> observe(command, session);
                case MAKE_MOVE -> move(command, session);
                case LEAVE -> leave(command, session);
                case RESIGN -> resign(command, session);
                case REDRAW_BOARD -> redraw(command, session);
                case HIGHLIGHT_MOVES -> highlightMoves(command, session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void join(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        String username = authDAO.getUsername(command.getAuthString());

        ServerMessageInterface serverMessage = new NotificationMessage(username + " joined the game");

        String message = gson.toJson(serverMessage);

        connectionManager.addConnection(command.getAuthString(), command.getGameID(), session);

        connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message);
    }
    private void observe(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        String username = authDAO.getUsername(command.getAuthString());

        ServerMessageInterface serverMessage = new NotificationMessage(username + " joined the game as observer");

        String message = gson.toJson(serverMessage);

        connectionManager.addConnection(command.getAuthString(), command.getGameID(), session);

        connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message);
    }
    private void move(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        try {
            game.makeMove(command.getMove());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameDAO.moveGame(command.getGameID(), game);

        ServerMessageInterface serverMessage = new LoadGameMessage(game);

        String message = gson.toJson(serverMessage);

        connectionManager.broadcastAll(command.getGameID(), message);
    }
    private void leave(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        String username = authDAO.getUsername(command.getAuthString());

        gameDAO.playerLeave(command.getGameID(), username);

        ServerMessageInterface serverMessage = new NotificationMessage(username + " left the game");

        String message = gson.toJson(serverMessage);

        connectionManager.removeConnection(command.getAuthString());

        connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message);
    }
    private void resign(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        String username = authDAO.getUsername(command.getAuthString());

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        game.gameOver();

        ServerMessageInterface serverMessage = new NotificationMessage(username + " resigned from the game");

        String message = gson.toJson(serverMessage);

        connectionManager.removeConnection(command.getAuthString());

        connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message);
    }
    private void redraw(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        ServerMessageInterface serverMessage = new LoadGameMessage(game);

        String message = gson.toJson(serverMessage);

        connectionManager.broadcastOne(command.getAuthString(), message);
    }
    private void highlightMoves(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        ServerMessageInterface serverMessage = new LoadGameMessage(game);

        ((LoadGameMessage) serverMessage).highlightMoves = true;

        ((LoadGameMessage) serverMessage).position = command.getPosition();

        String message = gson.toJson(serverMessage);

        connectionManager.broadcastAll(command.getGameID(), message);
    }
}
