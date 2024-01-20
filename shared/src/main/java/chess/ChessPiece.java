package chess;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    private Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition, ChessPiece bishop) {
        Collection<ChessMove> bishopMoveList = new ArrayList<>();

        int originalRow = myPosition.getRow();
        int originalCol = myPosition.getColumn();
        int row = originalRow;
        int col = originalCol;

        //Up-Right moves
        while (row != 7 && col != 7) {
            row++;
            col++;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != bishop.pieceColor) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }

        row = originalRow;
        col = originalCol;

        //Up-Left moves
        while (row != 7 && col != 0) {
            row++;
            col--;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != bishop.pieceColor) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }

        row = originalRow;
        col = originalCol;

        //Down-Right moves
        while (row != 0 && col != 7) {
            row--;
            col++;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != bishop.pieceColor) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }

        row = originalRow;
        col = originalCol;

        //Down-Left moves
        while (row != 0 && col != 0) {
            row--;
            col--;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != bishop.pieceColor) {
                bishopMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }
        return bishopMoveList;
    }

    private Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition, ChessPiece king) {
        Collection<ChessMove> kingMoveList = new ArrayList<>();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ChessPosition newPosition;

        //Up moves
        if (row < 7) {
            newPosition = new ChessPosition(row + 2, col + 1);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Down moves
        if (row > 0) {
            newPosition = new ChessPosition(row, col + 1);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Right moves
        if (col < 7) {
            newPosition = new ChessPosition(row + 1, col + 2);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Left moves
        if (col > 0) {
            newPosition = new ChessPosition(row + 1, col);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Up-Right moves
        if (row < 7 && col < 7) {
            newPosition = new ChessPosition(row + 2, col + 2);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Up-Left moves
        if (row < 7 && col > 0) {
            newPosition = new ChessPosition(row + 2, col);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Down-Right moves
        if (row > 0 && col < 7) {
            newPosition = new ChessPosition(row, col + 2);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Down-Left moves
        if (row > 0 && col > 0) {
            newPosition = new ChessPosition(row, col);
            if (board.getPiece(newPosition) == null) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != king.pieceColor) {
                kingMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }
        return kingMoveList;
    }

    private Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition, ChessPiece knight) {
        Collection<ChessMove> knightMoveList = new ArrayList<>();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ChessPosition newPosition;

        //Up-Right moves
        if (row < 6 && col < 7) {
            newPosition = new ChessPosition(row + 3, col + 2);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Up-Left moves
        if (row < 6 && col > 0) {
            newPosition = new ChessPosition(row + 3, col);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Right-Up moves
        if (col < 6 && row < 7) {
            newPosition = new ChessPosition(row + 2, col + 3);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Right-Down moves
        if (col < 6 && row > 0) {
            newPosition = new ChessPosition(row, col + 3);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Down-Right moves
        if (row > 1 && col < 7) {
            newPosition = new ChessPosition(row - 1, col + 2);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Down-Left moves
        if (row > 1 && col > 0) {
            newPosition = new ChessPosition(row - 1, col);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Left-Up moves
        if (row < 7 && col > 1) {
            newPosition = new ChessPosition(row + 2, col - 1);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }

        //Left-Down moves
        if (row > 0 && col > 1) {
            newPosition = new ChessPosition(row, col - 1);
            if (board.getPiece(newPosition) == null) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != knight.pieceColor) {
                knightMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else {

            }
        }
        return knightMoveList;
    }

    private Collection<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition, ChessPiece rook) {
        Collection<ChessMove> rookMoveList = new ArrayList<>();

        int originalRow = myPosition.getRow();
        int originalCol = myPosition.getColumn();
        int row = originalRow;
        int col = originalCol;

        //Up moves
        while (row != 7) {
            row++;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != rook.pieceColor) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }

        row = originalRow;

        //Down moves
        while (row != 0) {
            row--;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != rook.pieceColor) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }

        row = originalRow;

        //Right moves
        while (col != 7) {
            col++;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != rook.pieceColor) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }

        col = originalCol;

        //Left moves
        while (col != 0) {
            col--;

            ChessPosition newPosition = new ChessPosition(row + 1, col + 1);

            if (board.getPiece(newPosition) == null) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
            } else if (board.getPiece(newPosition).pieceColor != rook.pieceColor) {
                rookMoveList.add(new ChessMove(myPosition, newPosition, null));
                break;
            } else {
                break;
            }
        }
        return rookMoveList;
    }

    private Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition, ChessPiece queen) {
        Collection<ChessMove> queenMoveList = new ArrayList<>();
        Collection<ChessMove> rookMoveList = rookMoves(board, myPosition, queen);
        Collection<ChessMove> bishopMoveList = bishopMoves(board, myPosition, queen);

        queenMoveList.addAll(rookMoveList);
        queenMoveList.addAll(bishopMoveList);
        return queenMoveList;
    }

    private Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition, ChessPiece pawn) {
        Collection<ChessMove> pawnMoveList = new ArrayList<>();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ChessPosition newPosition;

        if (pawn.pieceColor == ChessGame.TeamColor.WHITE) {
            if (row < 6) {
                ChessPiece frontPiece = board.getPiece(new ChessPosition(row + 2, col + 1));

                if (frontPiece == null) {
                    newPosition = new ChessPosition(row + 2, col + 1);
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                }

                if (col < 7) {
                    ChessPiece rightPiece = board.getPiece(new ChessPosition(row + 2, col + 2));
                    if (rightPiece == null) {

                    } else if (rightPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row + 2, col + 2);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                    }
                }
                if (col > 0) {
                    ChessPiece leftPiece = board.getPiece(new ChessPosition(row + 2, col));
                    if (leftPiece == null) {

                    } else if (leftPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row + 2, col);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                    }
                }
            }

            if (row == 6) {
                ChessPiece frontPiece = board.getPiece(new ChessPosition(row + 2, col + 1));

                if (frontPiece == null) {
                    newPosition = new ChessPosition(row + 2, col + 1);
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.QUEEN));
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.ROOK));
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.KNIGHT));
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.BISHOP));
                }

                if (col < 7) {
                    ChessPiece rightPiece = board.getPiece(new ChessPosition(row + 2, col + 2));
                    if (rightPiece == null) {

                    } else if (rightPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row + 2, col + 2);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.QUEEN));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.ROOK));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.KNIGHT));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.BISHOP));
                    }
                }

                if (col > 0) {
                    ChessPiece leftPiece = board.getPiece(new ChessPosition(row + 2, col));
                    if (leftPiece == null) {

                    } else if (leftPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row + 2, col);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.QUEEN));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.ROOK));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.KNIGHT));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.BISHOP));
                    }
                }
            }

            if (row == 1) {
                ChessPiece frontPiece = board.getPiece(new ChessPosition(row + 2, col + 1));
                ChessPiece frontTwoPiece = board.getPiece(new ChessPosition(row + 3, col + 1));
                if (frontPiece == null && frontTwoPiece == null) {
                    newPosition = new ChessPosition(row + 3, col + 1);
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                }
            }
        }

        if (pawn.pieceColor == ChessGame.TeamColor.BLACK) {
            if (row > 1) {
                ChessPiece frontPiece = board.getPiece(new ChessPosition(row, col + 1));

                if (frontPiece == null) {
                    newPosition = new ChessPosition(row, col + 1);
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                }

                if (col < 7) {
                    ChessPiece rightPiece = board.getPiece(new ChessPosition(row, col + 2));
                    if (rightPiece == null) {

                    } else if (rightPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row, col + 2);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                    }
                }

                if (col > 0) {
                    ChessPiece leftPiece = board.getPiece(new ChessPosition(row, col));
                    if (leftPiece == null) {

                    } else if (leftPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row, col);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                    }
                }
            }

            if (row == 1) {
                ChessPiece frontPiece = board.getPiece(new ChessPosition(row, col + 1));

                if (frontPiece == null) {
                    newPosition = new ChessPosition(row, col + 1);
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.QUEEN));
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.ROOK));
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.KNIGHT));
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.BISHOP));
                }

                if (col < 7) {
                    ChessPiece rightPiece = board.getPiece(new ChessPosition(row, col + 2));
                    if (rightPiece == null) {

                    } else if (rightPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row, col + 2);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.QUEEN));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.ROOK));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.KNIGHT));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.BISHOP));
                    }
                }

                if (col > 0) {
                    ChessPiece leftPiece = board.getPiece(new ChessPosition(row, col));
                    if (leftPiece == null) {

                    } else if (leftPiece.pieceColor != pawn.pieceColor) {
                        newPosition = new ChessPosition(row, col);
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.QUEEN));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.ROOK));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.KNIGHT));
                        pawnMoveList.add(new ChessMove(myPosition, newPosition, PieceType.BISHOP));
                    }
                }
            }

            if (row == 6) {
                ChessPiece frontPiece = board.getPiece(new ChessPosition(row, col + 1));
                ChessPiece frontTwoPiece = board.getPiece(new ChessPosition(row - 1, col + 1));
                if (frontPiece == null && frontTwoPiece == null) {
                    newPosition = new ChessPosition(row - 1, col + 1);
                    pawnMoveList.add(new ChessMove(myPosition, newPosition, null));
                }
            }
        }
        return pawnMoveList;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moveList = new ArrayList<>();

        //Get chess piece
        ChessPiece pieceToMove = board.getPiece(myPosition);

        if (pieceToMove.getPieceType() == PieceType.BISHOP) {
            return bishopMoves(board, myPosition, pieceToMove);
        }

        if (pieceToMove.getPieceType() == PieceType.KING) {
            return kingMoves(board, myPosition, pieceToMove);
        }

        if (pieceToMove.getPieceType() == PieceType.KNIGHT) {
            return knightMoves(board, myPosition, pieceToMove);
        }

        if (pieceToMove.getPieceType() == PieceType.ROOK) {
            return rookMoves(board, myPosition, pieceToMove);
        }

        if (pieceToMove.getPieceType() == PieceType.QUEEN) {
            return queenMoves(board, myPosition, pieceToMove);
        }

        if (pieceToMove.getPieceType() == PieceType.PAWN) {
            return pawnMoves(board, myPosition, pieceToMove);
        }
        return moveList;
    }

    @Override
    public String toString() {
        return "{" + pieceColor +
                ", " + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
