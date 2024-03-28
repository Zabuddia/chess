package webSocketMessages.serverMessages;

import chess.ChessGame;
import chess.ChessPosition;

public class LoadGameMessage extends ServerMessage implements ServerMessageInterface {
    ChessGame game;
    public boolean highlightMoves = false;
    public ChessPosition position = null;
    public LoadGameMessage(ChessGame game) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
    }

    public ChessGame getGame() {
        return game;
    }
}
