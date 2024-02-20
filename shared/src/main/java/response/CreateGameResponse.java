package response;

public record CreateGameResponse(int responseNum, String message, String error, int gameID) {}