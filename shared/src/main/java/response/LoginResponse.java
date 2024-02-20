package response;

public record LoginResponse(int responseNum, String message, String error, String username, String authToken) {}