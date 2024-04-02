package server.websocket;

import chess.ChessGame;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataAccess.*;
import model.GameData;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import webSocketMessages.serverMessages.*;
import webSocketMessages.userCommands.*;

import java.io.IOException;
import java.util.Objects;

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
                case MAKE_MOVE -> move((MakeMoveCommand) command);
                case LEAVE -> leave(command);
                case RESIGN -> resign(command);
                case REDRAW_BOARD -> redraw(command);
                case HIGHLIGHT_MOVES -> highlightMoves(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void join(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        if (!authDAO.getAuth(command.getAuthString())) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: Unauthorized");

            String message = gson.toJson(serverMessage);

            connectionManager.addConnection(command.getAuthString(), command.getGameID(), session);

            connectionManager.broadcastOne(command.getAuthString(), message);

            connectionManager.removeConnection(command.getAuthString());

            return;
        }

        String username = authDAO.getUsername(command.getAuthString());

        int gameID = command.getGameID();

        if (!gameDAO.getGame(gameID)) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: No game with that ID");

            String message = gson.toJson(serverMessage);

            connectionManager.addConnection(command.getAuthString(), gameID, session);

            connectionManager.broadcastOne(command.getAuthString(), message);

            connectionManager.removeConnection(command.getAuthString());

            return;
        }

        ChessGame.TeamColor playerColor = command.getPlayerColor();

        String oldWhiteUsername = gameDAO.getGameData(gameID).whiteUsername();
        String oldBlackUsername = gameDAO.getGameData(gameID).blackUsername();

        if ((playerColor == ChessGame.TeamColor.WHITE && !Objects.equals(username, oldWhiteUsername)) || (playerColor == ChessGame.TeamColor.BLACK && !Objects.equals(username, oldBlackUsername))) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: A player has already joined the game with that color");

            String message = gson.toJson(serverMessage);

            connectionManager.addConnection(command.getAuthString(), gameID, session);

            connectionManager.broadcastOne(command.getAuthString(), message);

            connectionManager.removeConnection(command.getAuthString());
        } else {
            gameDAO.updateGame(command.getPlayerColor(), gameID, username);

            ServerMessageInterface serverMessage = new NotificationMessage(username + " joined the game as " + (playerColor == ChessGame.TeamColor.WHITE ? "white" : "black") + " player");

            String message = gson.toJson(serverMessage);

            connectionManager.addConnection(command.getAuthString(), command.getGameID(), session);

            connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message);

            ChessGame game = gameDAO.getGameData(command.getGameID()).game();

            ServerMessageInterface serverMessage2 = new LoadGameMessage(game);

            String message2 = gson.toJson(serverMessage2);

            connectionManager.broadcastOne(command.getAuthString(), message2);
        }
    }
    private void observe(GameCommand command, Session session) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        if (!authDAO.getAuth(command.getAuthString())) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: Unauthorized");

            String message = gson.toJson(serverMessage);

            connectionManager.addConnection(command.getAuthString(), command.getGameID(), session);

            connectionManager.broadcastOne(command.getAuthString(), message);

            connectionManager.removeConnection(command.getAuthString());

            return;
        }

        int gameID = command.getGameID();

        if (!gameDAO.getGame(gameID)) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: No game with that ID");

            String message = gson.toJson(serverMessage);

            connectionManager.addConnection(command.getAuthString(), gameID, session);

            connectionManager.broadcastOne(command.getAuthString(), message);

            connectionManager.removeConnection(command.getAuthString());

            return;
        }

        String username = authDAO.getUsername(command.getAuthString());

        ServerMessageInterface serverMessage = new NotificationMessage(username + " joined the game as observer");

        String message = gson.toJson(serverMessage);

        connectionManager.addConnection(command.getAuthString(), command.getGameID(), session);

        connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message);

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        ServerMessageInterface serverMessage2 = new LoadGameMessage(game);

        String message2 = gson.toJson(serverMessage2);

        connectionManager.broadcastOne(command.getAuthString(), message2);
    }
    private void move(MakeMoveCommand command) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();
        int gameID = command.getGameID();
        String whiteUsername = gameDAO.getGameData(gameID).whiteUsername();
        String blackUsername = gameDAO.getGameData(gameID).blackUsername();
        ChessGame game = gameDAO.getGameData(gameID).game();
        String username = authDAO.getUsername(command.getAuthString());
        if (!Objects.equals(username, whiteUsername) && !Objects.equals(username, blackUsername)) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: You are not a player in this game");
            String message = gson.toJson(serverMessage);
            connectionManager.broadcastOne(command.getAuthString(), message);
            return;
        }
        ChessGame.TeamColor turn = game.getTeamTurn();
        String turnString = turn == ChessGame.TeamColor.WHITE ? "white" : "black";
        ChessGame.TeamColor playerColor = command.getPlayerColor();
        if (playerColor == null && username.equals(turnString)) {
            try {
                game.makeMove(command.getMove());
            } catch (Exception e) {
                ServerMessageInterface serverMessage = new ErrorMessage("Error: Invalid move");
                String message = gson.toJson(serverMessage);
                connectionManager.broadcastOne(command.getAuthString(), message);
                return;
            }
            gameDAO.moveGame(command.getGameID(), game);
            ServerMessageInterface serverMessage = new LoadGameMessage(game);
            String message = gson.toJson(serverMessage);
            connectionManager.broadcastAll(command.getGameID(), message);
            ServerMessageInterface serverMessage2 = new NotificationMessage(username + " moved " + command.getMove().toString());
            String message2 = gson.toJson(serverMessage2);
            connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message2);
            return;
        }

        if (playerColor != turn) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: Not your turn");
            String message = gson.toJson(serverMessage);
            connectionManager.broadcastOne(command.getAuthString(), message);
            return;
        }
        try {
            game.makeMove(command.getMove());
        } catch (Exception e) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: Invalid move");
            String message = gson.toJson(serverMessage);
            connectionManager.broadcastOne(command.getAuthString(), message);
            return;
        }
        gameDAO.moveGame(command.getGameID(), game);
        ServerMessageInterface serverMessage = new LoadGameMessage(game);
        String message = gson.toJson(serverMessage);
        connectionManager.broadcastAll(command.getGameID(), message);
        ServerMessageInterface serverMessage2 = new NotificationMessage(username + " moved " + command.getMove().toString());
        String message2 = gson.toJson(serverMessage2);
        connectionManager.broadcastGroup(command.getAuthString(), command.getGameID(), message2);
        ChessGame.TeamColor opponentColor = playerColor == ChessGame.TeamColor.WHITE ? ChessGame.TeamColor.BLACK : ChessGame.TeamColor.WHITE;
        String opponentUsername = playerColor == ChessGame.TeamColor.WHITE ? blackUsername : whiteUsername;
        if (game.isInCheck(opponentColor)) {
            ServerMessageInterface serverMessage3 = new NotificationMessage(opponentUsername + " is in check");
            String message3 = gson.toJson(serverMessage3);
            connectionManager.broadcastAll(gameID, message3);
        }
        if (game.isInCheckmate(opponentColor)) {
            ServerMessageInterface serverMessage4 = new NotificationMessage(opponentUsername + " is in checkmate " + turnString + " wins!");
            String message4 = gson.toJson(serverMessage4);
            game.gameOver();
            connectionManager.broadcastAll(gameID, message4);
        }
        if (game.isInStalemate(opponentColor)) {
            ServerMessageInterface serverMessage5 = new NotificationMessage(opponentUsername + " is in stalemate. It's a tie!");
            String message5 = gson.toJson(serverMessage5);
            game.gameOver();
            connectionManager.broadcastAll(gameID, message5);
        }
    }
    private void leave(GameCommand command) throws IOException {
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
    private void resign(GameCommand command) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        int gameID = command.getGameID();

        GameData gameData = gameDAO.getGameData(gameID);

        String username = authDAO.getUsername(command.getAuthString());

        if (!Objects.equals(username, gameData.whiteUsername()) && !Objects.equals(username, gameData.blackUsername())) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: You are not a player in this game");

            String message = gson.toJson(serverMessage);

            connectionManager.broadcastOne(command.getAuthString(), message);

            return;
        }

        ChessGame game = gameDAO.getGameData(gameID).game();

        if (!game.getGameState()) {
            ServerMessageInterface serverMessage = new ErrorMessage("Error: Game is already over");

            String message = gson.toJson(serverMessage);

            connectionManager.broadcastOne(command.getAuthString(), message);

            return;
        }

        game.gameOver();

        gameDAO.moveGame(gameID, game);

        ServerMessageInterface serverMessage = new NotificationMessage(username + " resigned from the game");

        String message = gson.toJson(serverMessage);

        connectionManager.broadcastAll(command.getGameID(), message);

        connectionManager.removeConnection(command.getAuthString());
    }
    private void redraw(GameCommand command) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        ServerMessageInterface serverMessage = new LoadGameMessage(game);

        String message = gson.toJson(serverMessage);

        connectionManager.broadcastOne(command.getAuthString(), message);
    }
    private void highlightMoves(GameCommand command) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        Gson gson = builder.create();

        ChessGame game = gameDAO.getGameData(command.getGameID()).game();

        ServerMessageInterface serverMessage = new LoadGameMessage(game);

        ((LoadGameMessage) serverMessage).position = command.getPosition();

        String message = gson.toJson(serverMessage);

        connectionManager.broadcastOne(command.getAuthString(), message);
    }
}
