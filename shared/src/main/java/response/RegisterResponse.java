package response;

public record RegisterResponse(int responseNum, String message, String error, String username, String authToken) {}