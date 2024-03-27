package webSocketMessages.userCommands;

import chess.ChessMove;

public class MakeMoveCommand extends UserGameCommand implements GameCommand {
    int gameID;
    ChessMove move;
    public MakeMoveCommand(String authToken, int gameID, ChessMove move) {
        super(authToken);
        this.gameID = gameID;
        this.move = move;
        this.commandType = CommandType.MAKE_MOVE;
    }
    public int getGameID() {
        return gameID;
    }
}
