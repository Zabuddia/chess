package webSocketMessages.userCommands;

public class JoinObserverCommand extends UserGameCommand implements GameCommand {
    int gameID;
    JoinObserverCommand(String authToken, int gameID) {
        super(authToken);
        this.gameID = gameID;
        this.commandType = CommandType.JOIN_OBSERVER;
    }
    public int getGameID() {
        return gameID;
    }
}
