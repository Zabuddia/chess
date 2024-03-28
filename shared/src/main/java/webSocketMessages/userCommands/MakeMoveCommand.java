package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessMove;

public class MakeMoveCommand extends UserGameCommand implements GameCommand {
    int gameID;
    ChessMove move;
    ChessGame.TeamColor playerColor;
    public MakeMoveCommand(String authToken, ChessGame.TeamColor playerColor, int gameID, ChessMove move) {
        super(authToken);
        this.gameID = gameID;
        this.playerColor = playerColor;
        this.move = move;
        this.commandType = CommandType.MAKE_MOVE;
    }
    public int getGameID() {
        return gameID;
    }
    public ChessMove getMove() {
        return move;
    }

    @Override
    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }
}
