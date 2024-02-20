package service;

import chess.ChessGame;
import dataAccess.*;

public class JoinGameService {
    public String joinGame(String authToken, ChessGame.TeamColor clientColor, int gameID) {
        AuthDAO authDAO = new MemoryAuthDAO();
        if (!authDAO.getAuth(authToken)) {
            return "Unauthorized";
        }

        String username = authDAO.getUsername(authToken);

        GameDAO gameDAO = new MemoryGameDAO();
        if (!gameDAO.getGame(gameID)) {
            return "No game with that ID";
        }

        return gameDAO.updateGame(clientColor, gameID, username);
    }
}
