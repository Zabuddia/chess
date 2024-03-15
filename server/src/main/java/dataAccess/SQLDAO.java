package dataAccess;

import java.sql.ResultSet;
import java.sql.Statement;

import static java.sql.Types.NULL;

public class SQLDAO {
    public static boolean isEmpty(String tableName) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM " + tableName;
            try (var preparedStatement = conn.prepareStatement(statement)) {
                try (var resultSet = preparedStatement.executeQuery()) {
                    return !resultSet.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int tableSize(String tableName) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT COUNT(*) FROM " + tableName;
            try (var preparedStatement = conn.prepareStatement(statement)) {
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    protected int executeUpdate(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)) {
                for (var i = 0; i < params.length; i++) {
                    var param = params[i];
                    switch (param) {
                        case String p -> preparedStatement.setString(i + 1, p);
                        case Integer p -> preparedStatement.setInt(i + 1, p);
                        case null -> preparedStatement.setNull(i + 1, NULL);
                        default -> {
                        }
                    }
                }
                preparedStatement.executeUpdate();

                var rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            throw new DataAccessException("Unable to execute statement. " + e.getMessage());
        }
        return 0;
    }

    protected final String[] createStatements = {
            "CREATE TABLE IF NOT EXISTS user (" +
                    "username VARCHAR(255) PRIMARY KEY," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL" +
                    ");",
            "CREATE TABLE IF NOT EXISTS game (" +
                    "gameID INT PRIMARY KEY AUTO_INCREMENT," +
                    "whiteUsername VARCHAR(255)," +
                    "blackUsername VARCHAR(255)," +
                    "gameName VARCHAR(255) NOT NULL," +
                    "game BLOB NOT NULL" +
                    ");",
            "CREATE TABLE IF NOT EXISTS auth (" +
                    "authToken VARCHAR(255) PRIMARY KEY," +
                    "username VARCHAR(255) NOT NULL" +
                    ");"
    };

    protected void configureDatabase() {
        try {
            DatabaseManager.createDatabase();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    protected void deleteDatabase() {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "DROP DATABASE IF EXISTS " + DatabaseManager.getDatabaseName();
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}