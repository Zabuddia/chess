package server.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import webSocketMessages.serverMessages.NotificationMessage;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.serverMessages.ServerMessageDeserializer;
import webSocketMessages.serverMessages.ServerMessageInterface;
import webSocketMessages.userCommands.GameCommand;
import webSocketMessages.userCommands.UserGameCommand;
import webSocketMessages.userCommands.UserGameCommandDeserializer;

import java.io.IOException;

@WebSocket
public class WebSocketHandler {
    private final ConnectionManager connectionManager = new ConnectionManager();
    @OnWebSocketMessage
    public void onMessage(Session session, String msg) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UserGameCommand.class, new UserGameCommandDeserializer());
        Gson gson = builder.create();

        GameCommand command = gson.fromJson(msg, UserGameCommand.class);

        try {
            switch (command.getCommandType()) {
                case JOIN_PLAYER -> join(session);
                case JOIN_OBSERVER -> observe(session);
                case MAKE_MOVE -> move(session);
                case LEAVE -> leave(session);
                case RESIGN -> resign(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void join(Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ServerMessageInterface serverMessage = new NotificationMessage("Player joined the game");

        String message = gson.toJson(serverMessage);

        session.getRemote().sendString(message);
    }
    private void observe(Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ServerMessageInterface serverMessage = new NotificationMessage("Player joined the game as observer");

        String message = gson.toJson(serverMessage);

        session.getRemote().sendString(message);
    }
    private void move(Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ServerMessageInterface serverMessage = new NotificationMessage("Player moved a piece in the game");

        String message = gson.toJson(serverMessage);

        session.getRemote().sendString(message);
    }
    private void leave(Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ServerMessageInterface serverMessage = new NotificationMessage("Player left the game");

        String message = gson.toJson(serverMessage);

        session.getRemote().sendString(message);
    }
    private void resign(Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ServerMessageInterface serverMessage = new NotificationMessage("Player resigned from the game");

        String message = gson.toJson(serverMessage);

        session.getRemote().sendString(message);
    }
}
