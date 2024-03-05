package dataAccess;

import model.AuthData;

import java.util.UUID;

public class SQLAuthDAO extends SQLDAO implements AuthDAO {
    public SQLAuthDAO() {
        configureDatabase();
    }
    public void clearAuth() {
        var statement = "DELETE FROM auth";
        try {
            executeUpdate(statement);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public String createAuth(String username) {
        String authToken = UUID.randomUUID().toString();
        var statement = "INSERT INTO auth (authToken, username) VALUES (?, ?)";
        try {
            executeUpdate(statement, authToken, username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return authToken;
    }

    public boolean getAuth(String authToken) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM auth WHERE authToken = ?";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, authToken);
                try (var resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteAuth(String authToken) {
        var statement = "DELETE FROM auth WHERE authToken = ?";
        try {
            executeUpdate(statement, authToken);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

     public String getUsername(String authToken) {
         try (var conn = DatabaseManager.getConnection()) {
             var statement = "SELECT * FROM auth WHERE authToken = ?";
             try (var preparedStatement = conn.prepareStatement(statement)) {
                 preparedStatement.setString(1, authToken);
                 try (var resultSet = preparedStatement.executeQuery()) {
                     if (resultSet.next()) {
                         return resultSet.getString("username");
                     }
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
}
