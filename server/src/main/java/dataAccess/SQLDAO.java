package dataAccess;

import java.sql.ResultSet;
import java.sql.Statement;

public class SQLDAO {
    protected int executeUpdate(String statement, Object... args) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Or use another getXXX method if your key is not an int
                    } else {
                        throw new DataAccessException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (Exception e) {
            throw new DataAccessException("Unable to execute statement. " + e.getMessage());
        }
    }

    protected final String[] createStatements = {
            "CREATE TABLE IF NOT EXISTS user (" +
                    "username VARCHAR(255) PRIMARY KEY," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL" +
                    ");",
            "CREATE TABLE IF NOT EXISTS game (" +
                    "gameID INT PRIMARY KEY AUTO_INCREMENT," +
                    "gameName VARCHAR(255) NOT NULL" +
                    ");",
            "CREATE TABLE IF NOT EXISTS auth (" +
                    "authToken VARCHAR(255) PRIMARY KEY," +
                    "username VARCHAR(255) NOT NULL," +
                    "FOREIGN KEY (username) REFERENCES user(username)" +
                    ");"
    };
    protected void configureDatabase(){
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT COUNT(*) AS count FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, DatabaseManager.getDatabaseName());
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getInt("count") == 0) {
                            DatabaseManager.createDatabase();
                            for (var string : createStatements) {
                                try (var ps = conn.prepareStatement(string)) {
                                    ps.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
