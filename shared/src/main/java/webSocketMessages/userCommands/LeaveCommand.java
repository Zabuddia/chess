package webSocketMessages.userCommands;

public class LeaveCommand extends UserGameCommand implements GameCommand {
    int gameID;
    public LeaveCommand(String authToken, int gameID) {
        super(authToken);
        this.gameID = gameID;
        this.commandType = CommandType.LEAVE;
    }
    public int getGameID() {
        return gameID;
    }
}
