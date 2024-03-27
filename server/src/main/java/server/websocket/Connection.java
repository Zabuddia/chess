package server.websocket;

import org.eclipse.jetty.websocket.api.Session;

public class Connection {
    public Session session;
    public String authToken;
    public Connection(Session session, String authToken) {
        this.session = session;
        this.authToken = authToken;
    }
    public Session getSession() {
        return session;
    }
    public String getAuthString() {
        return authToken;
    }
    public void send(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
