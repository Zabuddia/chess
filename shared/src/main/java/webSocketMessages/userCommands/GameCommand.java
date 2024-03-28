package webSocketMessages.userCommands;

import chess.ChessMove;

public interface GameCommand {
    public String getAuthString();
    public UserGameCommand.CommandType getCommandType();
    public int getGameID();
    public ChessMove getMove();
}
