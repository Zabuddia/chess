package server.websocket;

import org.eclipse.jetty.websocket.api.Session;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<>();

    public void addConnection(String authToken, Session session) {
        Connection connection = new Connection(session, authToken);
        connections.put(authToken, connection);
    }

    public void removeConnection(String authToken) {
        connections.remove(authToken);
    }

    public Connection getConnection(String authToken, Session session) {
        return connections.get(authToken);
    }

    public void broadcastMessage(String senderAuthToken, String message) {
        connections.forEach((authToken, connection) -> {
            if (!authToken.equals(senderAuthToken) && connection.getSession().isOpen()) {
                try {
                    connection.getSession().getRemote().sendString(message);
                } catch (Exception e) {
                    System.err.println("Failed to send message to " + authToken + ": " + e.getMessage());
                }
            }
        });
    }

    public void sendMessage(String authToken, String message) {
        Connection connection = connections.get(authToken);
        if (connection != null && connection.getSession().isOpen()) {
            try {
                connection.getSession().getRemote().sendString(message);
            } catch (Exception e) {
                System.err.println("Failed to send message to " + authToken + ": " + e.getMessage());
            }
        }
    }

    public void closeAllConnections() {
        connections.forEach((authToken, connection) -> {
            try {
                connection.getSession().disconnect();
            } catch (Exception e) {
                System.err.println("Failed to close connection for " + authToken);
            }
        });
        connections.clear();
    }
}
