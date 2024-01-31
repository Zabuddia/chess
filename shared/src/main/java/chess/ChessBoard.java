package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private final ChessPiece[][] squares;
    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean whiteRookQueensideMoved;
    private boolean whiteRookKingsideMoved;
    private boolean blackRookQueensideMoved;
    private boolean blackRookKingsideMoved;

    public ChessBoard() {
        this.squares = new ChessPiece[8][8];
        whiteKingMoved = blackKingMoved = whiteRookQueensideMoved = whiteRookKingsideMoved = blackRookQueensideMoved = blackRookKingsideMoved = false;
    }

    public ChessBoard(ChessBoard copyBoard) {
        this.squares = new ChessPiece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyBoard.squares[i][j] != null) {
                    this.squares[i][j] = new ChessPiece(copyBoard.squares[i][j].getTeamColor(), copyBoard.squares[i][j].getPieceType());
                }
            }
        }
        this.whiteKingMoved = copyBoard.whiteKingMoved;
        this.blackKingMoved = copyBoard.blackKingMoved;
        this.whiteRookQueensideMoved = copyBoard.whiteRookQueensideMoved;
        this.whiteRookKingsideMoved = copyBoard.whiteRookKingsideMoved;
        this.blackRookQueensideMoved = copyBoard.blackRookQueensideMoved;
        this.blackRookKingsideMoved = copyBoard.blackRookKingsideMoved;
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()][position.getColumn()] = piece;
    }

    public void removePiece(ChessPosition position) {
        squares[position.getRow()][position.getColumn()] = null;
    }
    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()][position.getColumn()];
    }

    public ChessPosition getKingPosition(ChessGame.TeamColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null && squares[i][j].getTeamColor() == color && squares[i][j].getPieceType() == ChessPiece.PieceType.KING) {
                    return new ChessPosition(i + 1, j + 1);
                }
            }
        }
        return null;
    }

    public Collection<ChessPosition> occupiedPositions() {
        Collection<ChessPosition> occupiedPositionsList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null) {
                    occupiedPositionsList.add(new ChessPosition(i + 1, j + 1));
                }
            }
        }
        return occupiedPositionsList;
    }

    public Collection<ChessPosition> occupiedPositionsOfAColor(ChessGame.TeamColor color) {
        Collection<ChessPosition> occupiedPositionsList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null && squares[i][j].getTeamColor() == color) {
                    occupiedPositionsList.add(new ChessPosition(i + 1, j + 1));
                }
            }
        }
        return occupiedPositionsList;
    }

    public boolean isKingMoved(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            return whiteKingMoved;
        } else {
            return blackKingMoved;
        }
    }

    public boolean isRookQueensideMoved(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            return whiteRookQueensideMoved;
        } else {
            return blackRookQueensideMoved;
        }
    }

    public boolean isRookKingsideMoved(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            return whiteRookKingsideMoved;
        } else {
            return blackRookKingsideMoved;
        }
    }

    public boolean isRookQueensideThere(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE && squares[0][0] != null && squares[0][0].getPieceType() == ChessPiece.PieceType.ROOK && squares[0][0].getTeamColor() == ChessGame.TeamColor.WHITE) {
            return true;
        } else return color == ChessGame.TeamColor.BLACK && squares[7][0] != null && squares[7][0].getPieceType() == ChessPiece.PieceType.ROOK && squares[7][0].getTeamColor() == ChessGame.TeamColor.BLACK;
    }

    public boolean isRookKingsideThere(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE && squares[0][7] != null && squares[0][7].getPieceType() == ChessPiece.PieceType.ROOK && squares[0][7].getTeamColor() == ChessGame.TeamColor.WHITE) {
            return true;
        } else return color == ChessGame.TeamColor.BLACK && squares[7][7] != null && squares[7][7].getPieceType() == ChessPiece.PieceType.ROOK && squares[7][7].getTeamColor() == ChessGame.TeamColor.BLACK;
    }

    public void setKingMoved(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            whiteKingMoved = true;
        } else {
            blackKingMoved = true;
        }
    }

    public void setRookMoved(ChessPosition rookPosition, ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            if (rookPosition.getColumn() == 0 && rookPosition.getRow() == 0) {
                whiteRookQueensideMoved = true;
            } else if (rookPosition.getColumn() == 7 && rookPosition.getRow() == 0){
                whiteRookKingsideMoved = true;
            }
        } else {
            if (rookPosition.getColumn() == 0 && rookPosition.getRow() == 7) {
                blackRookQueensideMoved = true;
            } else if (rookPosition.getColumn() == 7 && rookPosition.getRow() == 7){
                blackRookKingsideMoved = true;
            }
        }
    }

    public boolean castleQueensideSpacesEmpty(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            for (ChessPosition position : occupiedPositions()) {
                if (position.getRow() == 0 && ((position.getColumn() == 1) || (position.getColumn() == 2) || (position.getColumn() == 3))) {
                    return false;
                }
            }
        } else {
            for (ChessPosition position : occupiedPositions()) {
                if (position.getRow() == 7 && ((position.getColumn() == 1) || (position.getColumn() == 2) || (position.getColumn() == 3))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean castleKingsideSpacesEmpty(ChessGame.TeamColor color) {
        if (color == ChessGame.TeamColor.WHITE) {
            for (ChessPosition position : occupiedPositions()) {
                if (position.getRow() == 0 && ((position.getColumn() == 5) || (position.getColumn() == 6))) {
                    return false;
                }
            }
        } else {
            for (ChessPosition position : occupiedPositions()) {
                if (position.getRow() == 7 && ((position.getColumn() == 5) || (position.getColumn() == 6))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isRookQueensideInDanger(ChessMove move) {
        if (move.endPosition().getRow() == 0 && move.endPosition().getColumn() == 2) {
            for (ChessPosition occupiedPosition : occupiedPositionsOfAColor(ChessGame.TeamColor.BLACK)) {
                ChessPiece piece = getPiece(occupiedPosition);
                for (ChessMove opponentPotentialMove : piece.pieceMoves(this, occupiedPosition)) {
                    if (opponentPotentialMove.endPosition().equals(new ChessPosition(1, 4))) {
                        return true;
                    }
                }
            }
        } else if (move.endPosition().getRow() == 7 && move.endPosition().getColumn() == 2) {
            for (ChessPosition occupiedPosition : occupiedPositionsOfAColor(ChessGame.TeamColor.WHITE)) {
                ChessPiece piece = getPiece(occupiedPosition);
                for (ChessMove opponentPotentialMove : piece.pieceMoves(this, occupiedPosition)) {
                    if (opponentPotentialMove.endPosition().equals(new ChessPosition(8, 4))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isRookKingsideInDanger(ChessMove move) {
        if (move.endPosition().getRow() == 0 && move.endPosition().getColumn() == 6) {
            for (ChessPosition occupiedPosition : occupiedPositionsOfAColor(ChessGame.TeamColor.BLACK)) {
                ChessPiece piece = getPiece(occupiedPosition);
                for (ChessMove opponentPotentialMove : piece.pieceMoves(this, occupiedPosition)) {
                    if (opponentPotentialMove.endPosition().equals(new ChessPosition(1, 6))) {
                        return true;
                    }
                }
            }
        } else if (move.endPosition().getRow() == 7 && move.endPosition().getColumn() == 6) {
            for (ChessPosition occupiedPosition : occupiedPositionsOfAColor(ChessGame.TeamColor.WHITE)) {
                ChessPiece piece = getPiece(occupiedPosition);
                for (ChessMove opponentPotentialMove : piece.pieceMoves(this, occupiedPosition)) {
                    if (opponentPotentialMove.endPosition().equals(new ChessPosition(8, 6))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void castleRook(ChessGame.TeamColor color, ChessPosition kingPosition) {
        if (color == ChessGame.TeamColor.WHITE && kingPosition.getColumn() == 2) {
            ChessPosition originalRookPosition = new ChessPosition(1, 1);
            ChessPiece rook = getPiece(originalRookPosition);
            ChessPosition newRookPosition = new ChessPosition(1, 4);
            addPiece(newRookPosition, rook);
            removePiece(originalRookPosition);
        } else if (color == ChessGame.TeamColor.WHITE && kingPosition.getColumn() == 6) {
            ChessPosition originalRookPosition = new ChessPosition(1, 8);
            ChessPiece rook = getPiece(originalRookPosition);
            ChessPosition newRookPosition = new ChessPosition(1, 6);
            addPiece(newRookPosition, rook);
            removePiece(originalRookPosition);
        }

        if (color == ChessGame.TeamColor.BLACK && kingPosition.getColumn() == 2) {
            ChessPosition originalRookPosition = new ChessPosition(8, 1);
            ChessPiece rook = getPiece(originalRookPosition);
            ChessPosition newRookPosition = new ChessPosition(8, 4);
            addPiece(newRookPosition, rook);
            removePiece(originalRookPosition);
        } else if (color == ChessGame.TeamColor.BLACK && kingPosition.getColumn() == 6) {
            ChessPosition originalRookPosition = new ChessPosition(8, 8);
            ChessPiece rook = getPiece(originalRookPosition);
            ChessPosition newRookPosition = new ChessPosition(8, 6);
            addPiece(newRookPosition, rook);
            removePiece(originalRookPosition);
        }
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //Reset every square to null
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = null;
            }
        }

        //Put all the white pieces in the right beginning spot
        squares[0][0] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        squares[0][1] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        squares[0][2] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        squares[0][3] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        squares[0][4] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        squares[0][5] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        squares[0][6] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        squares[0][7] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        squares[1][0] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][1] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][2] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][3] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][4] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][5] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][6] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        squares[1][7] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

        //Put all the black pieces in the right beginning spot
        squares[7][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        squares[7][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        squares[7][2] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        squares[7][3] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        squares[7][4] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        squares[7][5] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        squares[7][6] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        squares[7][7] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        squares[6][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][2] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][3] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][4] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][5] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][6] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        squares[6][7] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(squares[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Arrays.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }
}
