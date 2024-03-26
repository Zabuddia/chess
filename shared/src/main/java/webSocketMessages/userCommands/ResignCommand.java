package webSocketMessages.userCommands;

public class ResignCommand extends UserGameCommand implements GameCommand {
    int gameID;
    public ResignCommand(String authToken, int gameID) {
        super(authToken);
        this.gameID = gameID;
        this.commandType = CommandType.RESIGN;
    }
}
