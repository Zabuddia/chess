package server;

import handler.*;
import server.websocket.WebSocketHandler;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        Spark.webSocket("/connect", WebSocketHandler.class);

        // Register your endpoints and handle exceptions here.
        Spark.delete("/db", (req, res) -> (new ClearHandler()).handleClear(req, res));
        Spark.post("/user", (req, res) -> (new RegisterHandler()).handleRegister(req, res));
        Spark.post("/session", (req, res) -> (new LoginHandler()).handleLogin(req, res));
        Spark.delete("/session", (req, res) -> (new LogoutHandler()).handleLogout(req, res));
        Spark.get("/game", (req, res) -> (new ListGamesHandler()).handleListGames(req, res));
        Spark.post("/game", (req, res) -> (new CreateGameHandler()).handleCreateGame(req, res));
        Spark.put("/game", (req, res) -> (new JoinGameHandler()).handleJoinGame(req, res));

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}