package response;

public record LoginResponse(String message, String error, String username, String authToken) {}