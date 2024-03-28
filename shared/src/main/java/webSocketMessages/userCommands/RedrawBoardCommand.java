package webSocketMessages.userCommands;

public class RedrawBoardCommand extends UserGameCommand implements GameCommand {
    int gameID;
    public RedrawBoardCommand(String authToken, int gameID) {
        super(authToken);
        this.gameID = gameID;
        this.commandType = CommandType.REDRAW_BOARD;
    }
    public int getGameID() {
        return gameID;
    }
}
