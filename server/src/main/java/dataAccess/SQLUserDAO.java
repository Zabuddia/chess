package dataAccess;

import model.UserData;

import javax.xml.crypto.Data;

public class SQLUserDAO extends SQLDAO implements UserDAO {
    public SQLUserDAO() {

        configureDatabase();
    }
    public void clearUser() {
        var statement = "DELETE FROM user";
        try {
            executeUpdate(statement);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String username, String password, String email) {
        var statement = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        try {
            executeUpdate(statement, username, password, email);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public UserData getUser(String username) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM user WHERE username = ?";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, username);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new UserData(
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("email")
                        );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyUser(String username, String password) {
        var user = getUser(username);
        if (user == null) {
            return false;
        }
        return user.password().equals(password);
    }
}
