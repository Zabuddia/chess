package service;

import chess.ChessGame;
import dataAccess.*;
import request.JoinGameRequest;
import response.JoinGameResponse;

import java.util.Objects;

public class JoinGameService {
    private final AuthDAO authDAO = new MemoryAuthDAO();
    private final GameDAO gameDAO = new MemoryGameDAO();
    public boolean validateAuth(String authToken) {
        return authDAO.getAuth(authToken);
    }
    public JoinGameResponse joinGame(JoinGameRequest joinGameRequest, String authToken) {
        if (!validateAuth(authToken)) {
            return new JoinGameResponse("Error: unauthorized");
        }
        ChessGame.TeamColor clientColor = joinGameRequest.playerColor();
        int gameID = joinGameRequest.gameID();

        String username = authDAO.getUsername(authToken);

        if (!gameDAO.getGame(gameID)) {
            return new JoinGameResponse("Error: bad request");
        }

        String response = gameDAO.updateGame(clientColor, gameID, username);

        if (Objects.equals(response, "No game with that ID")) {
            return new JoinGameResponse("Error: bad request");
        }
        if (Objects.equals(response, "Already taken")) {
            return new JoinGameResponse("Error: already taken");
        }

        return new JoinGameResponse(null);
    }
}
