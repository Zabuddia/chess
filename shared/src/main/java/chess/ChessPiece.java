package chess;

import javax.lang.model.type.NullType;
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
        Collection<ChessMove> moveList = new ArrayList<>();

        //Get chess piece
        ChessPiece pieceToMove = board.getPiece(myPosition);

        //What to do if the piece is a pawn
        if (pieceToMove.getPieceType() == PieceType.PAWN) {
            //Do nothing if the pawn is at the end of the board
            if (myPosition.getRow() == 7) {

            } else {
                //If there is a black piece to the diagonal left of the pawn
                ChessPiece diagonalLeftPiece = board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1));
                if (myPosition.getColumn() == 0) {
                    //If the pawn is on the very left of the board
                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1), PieceType.PAWN));
                }
                //If there is a black piece to the diagonal right of the pawn
                ChessPiece diagonalRightPiece = board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1));
                if (myPosition.getColumn() == 7) {
                    //If the pawn is on the very right of the board
                }else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1), PieceType.PAWN));
                }
                //If there is a piece in front of the pawn
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) != null) {

                } else{
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
                    //If there is a piece two spaces in front of the pawn
                    if (board.getPiece(new ChessPosition(3, myPosition.getColumn())) != null) {

                    } else if (myPosition.getRow() == 1) {
                        //If the pawn hasn't been moved it can go two spaces
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.PAWN));
                    }
                }
            }
        }

        //What to do if the piece is a bishop
        if (pieceToMove.getPieceType() == PieceType.BISHOP) {
            //Save original row and column
            int originalRow = myPosition.getRow();
            int originalCol = myPosition.getColumn();
            //Moves to the top right of the bishop
            int row = originalRow;
            int col = originalCol;
            while (row != 8 && col != 8) {
                row++;
                col++;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row - 1, col - 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                } else if (board.getPiece(new ChessPosition(row - 1, col - 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the top left of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 8 && col != 1) {
                row++;
                col--;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row - 1, col - 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                } else if (board.getPiece(new ChessPosition(row - 1, col - 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the bottom right of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 1 && col != 8) {
                row--;
                col++;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row - 1, col - 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                } else if (board.getPiece(new ChessPosition(row - 1, col - 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the bottom left of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 1 && col != 1) {
                row--;
                col--;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row - 1, col - 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                } else if (board.getPiece(new ChessPosition(row - 1, col - 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row, col), PieceType.BISHOP));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
        }
        return moveList;
    }
}
