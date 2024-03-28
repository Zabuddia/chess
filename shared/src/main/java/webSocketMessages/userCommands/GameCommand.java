package webSocketMessages.userCommands;

import chess.ChessMove;
import chess.ChessPosition;

public interface GameCommand {
    public String getAuthString();
    public UserGameCommand.CommandType getCommandType();
    public int getGameID();
    public ChessMove getMove();
    public ChessPosition getPosition();
}
