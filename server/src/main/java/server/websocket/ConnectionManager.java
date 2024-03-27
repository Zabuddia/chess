package server.websocket;

import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessageInterface;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<String, Integer> gameConnections = new ConcurrentHashMap<>();

    public void addConnection(String authToken, int gameID, Session session) {
        var connection = new Connection(session, authToken);
        connections.put(authToken, connection);
        gameConnections.put(authToken, gameID);
    }

    public void removeConnection(String authToken) {
        connections.remove(authToken);
        gameConnections.remove(authToken);
    }

    public void broadcastGroup(String excludeAuthToken, int gameID, String message) {
        connections.forEach((id, connection) -> {
            if (gameConnections.get(id) == gameID && !connection.getAuthString().equals(excludeAuthToken)) {
                connection.send(message);
            }
        });
    }

    public void broadcastOne(String authToken, String message) {
        connections.get(authToken).send(message);
    }

    public void broadcastAll(int gameID, String message) {
        connections.forEach((id, connection) -> {
            if (gameConnections.get(id) == gameID) {
                connection.send(message);
            }
        });
    }

}
