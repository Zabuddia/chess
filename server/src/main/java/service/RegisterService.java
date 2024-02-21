package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;
import model.UserData;
import request.RegisterRequest;
import response.RegisterResponse;

public class RegisterService {
    private final UserDAO userDAO = new MemoryUserDAO();
    private final AuthDAO authDAO = new MemoryAuthDAO();
    public RegisterResponse register(RegisterRequest registerRequest) {
        String username = registerRequest.username();
        String password = registerRequest.password();
        String email = registerRequest.email();
        UserData user = userDAO.getUser(username);
        if (user != null) {
            return new RegisterResponse("Error: already taken", null, null);
        }
        if (password == null) {
            return new RegisterResponse("Error: bad request", null, null);
        }
        userDAO.createUser(username, password, email);
        return new RegisterResponse(null, username, authDAO.createAuth(username));
    }
}
