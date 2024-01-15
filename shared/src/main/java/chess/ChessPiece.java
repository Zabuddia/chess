package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //Initialize ArrayList
        ArrayList<ChessMove> moveList = new ArrayList<>();

        //Get chess piece
        ChessPiece pieceToMove = board.getPiece(myPosition);

        ChessPiece frontPiece = board.getPiece(myPosition.getColumn())

        //What to do if the piece is a pawn
        if (pieceToMove.getPieceType() == PieceType.PAWN) {
            //If the pawn is at the second-to-last square
            if (myPosition.getRow() == 6) {
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), PieceType.QUEEN));
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), PieceType.BISHOP));
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), PieceType.KNIGHT));
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), PieceType.ROOK));
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), PieceType.PAWN));
            } else {
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), PieceType.PAWN));
            }
            //If the pawn hasn't been moved it can go two spaces
            if (myPosition.getRow() == 1) {
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.PAWN));
            }
            //If there is a black piece to the diagonal left of the pawn
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1), PieceType.PAWN));
            }
            //If there is a black piece to the diagonal right of the pawn
            if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1)).getTeamColor() == ChessGame.TeamColor.BLACK) {
                moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1), PieceType.PAWN));
            }
        }
    }
}
