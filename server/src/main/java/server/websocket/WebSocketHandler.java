package server.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import webSocketMessages.userCommands.GameCommand;
import webSocketMessages.userCommands.UserGameCommand;
import webSocketMessages.userCommands.UserGameCommandDeserializer;

@WebSocket
public class WebSocketHandler {
    private final ConnectionManager connectionManager = new ConnectionManager();
    @OnWebSocketMessage
    public void onMessage(Session session, String msg) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UserGameCommand.class, new UserGameCommandDeserializer());
        Gson gson = builder.create();

        GameCommand command = gson.fromJson(msg, UserGameCommand.class);

        var conn = connectionManager.getConnection(command.getAuthString(), session);
        if (conn != null) {
            switch (command.getCommandType()) {
                case JOIN_PLAYER -> join(conn, msg);
                case JOIN_OBSERVER -> observe(conn, msg);
                case MAKE_MOVE -> move(conn, msg);
                case LEAVE -> leave(conn, msg);
                case RESIGN -> resign(conn, msg);
            }
        } else {
            Connection.sendError("unknown user");
        }
    }
    private void join(Connection conn, String msg) {
        connectionManager.broadcastMessage(conn.getAuthToken(), msg);
    }
    private void observe(Connection conn, String msg) {
        connectionManager.broadcastMessage(conn.getAuthToken(), msg);
    }
    private void move(Connection conn, String msg) {
        connectionManager.broadcastMessage(conn.getAuthToken(), msg);
    }
    private void leave(Connection conn, String msg) {
        connectionManager.broadcastMessage(conn.getAuthToken(), msg);
    }
    private void resign(Connection conn, String msg) {
        connectionManager.broadcastMessage(conn.getAuthToken(), msg);
    }
}
