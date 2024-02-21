package response;

public record RegisterResponse(String message, String error, String username, String authToken) {}