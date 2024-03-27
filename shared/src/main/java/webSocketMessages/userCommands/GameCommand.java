package webSocketMessages.userCommands;

public interface GameCommand {
    public String getAuthString();
    public UserGameCommand.CommandType getCommandType();
    public int getGameID();
}
