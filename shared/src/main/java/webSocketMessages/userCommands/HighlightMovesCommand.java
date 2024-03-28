package webSocketMessages.userCommands;

import chess.ChessPosition;

public class HighlightMovesCommand extends UserGameCommand implements GameCommand {
    int gameID;
    ChessPosition position;
    public HighlightMovesCommand(String authToken, int gameID, ChessPosition position) {
        super(authToken);
        this.gameID = gameID;
        this.commandType = CommandType.HIGHLIGHT_MOVES;
        this.position = position;
    }
    public int getGameID() {
        return gameID;
    }
    public ChessPosition getPosition() {
        return position;
    }
}
