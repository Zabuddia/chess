package server.websocket;

import org.eclipse.jetty.websocket.api.Session;

public class Connection {
    private static Session session;
    private final int gameID;
    public Connection(Session session, int gameID) {
        this.session = session;
        this.gameID = gameID;
    }
    public Session getSession() {
        return session;
    }
    public int getGameID() {
        return gameID;
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
