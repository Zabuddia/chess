package server.websocket;

import org.eclipse.jetty.websocket.api.Session;

public class Connection {
    private static Session session;
    private final String authToken;
    public Connection(Session session, String authToken) {
        this.session = session;
        this.authToken = authToken;
    }
    public Session getSession() {
        return session;
    }
    public String getAuthToken() {
        return authToken;
    }
    public static void send(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendError(String message) {
        try {
            session.getRemote().sendString("{\"error\":\"" + message + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
