package dataAccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;

import java.util.ArrayList;

public class SQLGameDAO extends SQLDAO implements GameDAO {
    private final Gson gson = new Gson();

    public SQLGameDAO() {
        configureDatabase();
    }

    public void clearGame() {
        var statement = "DELETE FROM game";
        try {
            executeUpdate(statement);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public int createGame(String gameName) {
        int id = -1;
        ChessGame game = new ChessGame();
        game.getBoard().resetBoard();
        String gameString = gson.toJson(game);
        var statement = "INSERT INTO game (gameName, game) VALUES (?, ?)";
        try {
            id = executeUpdate(statement, gameName, gameString);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<GameData> listGames() {
        ArrayList<GameData> gameList = new ArrayList<>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                try (var resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int gameID = resultSet.getInt("gameID");
                        String whiteUsername = resultSet.getString("whiteUsername");
                        String blackUsername = resultSet.getString("blackUsername");
                        String gameName = resultSet.getString("gameName");
                        String gameString = resultSet.getString("game");
                        ChessGame game = gson.fromJson(gameString, ChessGame.class);
                        gameList.add(new GameData(gameID, whiteUsername, blackUsername, gameName, game));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameList;
    }

    public boolean getGame(int gameID) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game WHERE gameID = ?";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setInt(1, gameID);
                try (var resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public GameData getGameData(int gameID) {
        GameData gameData = null;
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game WHERE gameID = ?";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setInt(1, gameID);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String whiteUsername = resultSet.getString("whiteUsername");
                        String blackUsername = resultSet.getString("blackUsername");
                        String gameName = resultSet.getString("gameName");
                        String gameString = resultSet.getString("game");
                        ChessGame game = gson.fromJson(gameString, ChessGame.class);
                        gameData = new GameData(gameID, whiteUsername, blackUsername, gameName, game);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameData;
    }

    public String updateGame(ChessGame.TeamColor clientColor, int gameID, String username) {
        if (!getGame(gameID)) {
            return "No game with that ID";
        }

        String whiteUsername = null;
        String blackUsername = null;
        String gameName = null;
        ChessGame game = null;

        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game WHERE gameID = ?";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setInt(1, gameID);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        whiteUsername = resultSet.getString("whiteUsername");
                        blackUsername = resultSet.getString("blackUsername");
                        gameName = resultSet.getString("gameName");
                        String gameString = resultSet.getString("game");
                        game = gson.fromJson(gameString, ChessGame.class);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clientColor == ChessGame.TeamColor.WHITE && whiteUsername == null) {
            whiteUsername = username;
        } else if (clientColor == ChessGame.TeamColor.WHITE) {
            return "Already taken";
        }

        if (clientColor == ChessGame.TeamColor.BLACK && blackUsername == null) {
            blackUsername = username;
        } else if (clientColor == ChessGame.TeamColor.BLACK) {
            return "Already taken";
        }

        var statement = "DELETE FROM game WHERE gameID = ?";
        try {
            executeUpdate(statement, gameID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        String gameString = gson.toJson(game);
        var makeGame = "INSERT INTO game (gameID, whiteUsername, blackUsername, gameName, game) VALUES (?, ?, ?, ?, ?)";
        try {
            executeUpdate(makeGame, gameID, whiteUsername, blackUsername, gameName, gameString);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "Successfully updated game";
    }
}