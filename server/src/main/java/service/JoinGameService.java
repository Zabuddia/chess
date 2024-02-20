package service;

import chess.ChessGame;
import dataAccess.*;
import response.JoinGameResponse;

import java.util.Objects;

public class JoinGameService {
    public JoinGameResponse joinGame(String authToken, ChessGame.TeamColor clientColor, int gameID) {
        AuthDAO authDAO = new MemoryAuthDAO();
        if (!authDAO.getAuth(authToken)) {
            return new JoinGameResponse(401, "message", "Error: unauthorized");
        }

        String username = authDAO.getUsername(authToken);

        GameDAO gameDAO = new MemoryGameDAO();
        if (!gameDAO.getGame(gameID)) {
            return new JoinGameResponse(400, "message", "Error: bad request");
        }

        String response = gameDAO.updateGame(clientColor, gameID, username);

        if (Objects.equals(response, "No game with that ID")) {
            return new JoinGameResponse(400, "message", "Error: bad request");
        }
        if (Objects.equals(response, "Already taken")) {
            return new JoinGameResponse(403, "message", "Error: already taken");
        }

        return new JoinGameResponse(200, null, null);
    }
}
