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
        //Initialize ArrayList
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

//        //What to do if the piece is a white pawn
//        if (pieceToMove.getPieceType() == PieceType.PAWN && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
//            //Do nothing if the pawn is at the end of the board
//            if (myPosition.getRow() == 7) {
//
//            } else {
//                //If there is a black piece to the diagonal left of the pawn
//                ChessPiece diagonalLeftPiece = board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()));
//                if (myPosition.getColumn() == 0) {
//                    //If the pawn is on the very left of the board
//                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() != 6) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
//                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() == 6) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.QUEEN));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.ROOK));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.BISHOP));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.KNIGHT));
//                }
//                //If there is a black piece to the diagonal right of the pawn
//                ChessPiece diagonalRightPiece = board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2));
//                if (myPosition.getColumn() == 7) {
//                    //If the pawn is on the very right of the board
//                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() != 6) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
//                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() == 6) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.QUEEN));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.ROOK));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.BISHOP));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.KNIGHT));
//                }
//                //If there is a piece in front of the pawn
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null) {
//
//                } else{
//                    //If the pawn is at the second-to-last square
//                    if (myPosition.getRow() == 6) {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.QUEEN));
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.ROOK));
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.BISHOP));
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.KNIGHT));
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
//                    }
//                    //If there is a piece two spaces in front of the pawn
//                    if (board.getPiece(new ChessPosition(4, myPosition.getColumn() + 1)) != null) {
//
//                    } else if (myPosition.getRow() == 1) {
//                        //If the pawn hasn't been moved it can go two spaces
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 1), null));
//                    }
//                }
//            }
//        }
//
//        //What to do if the piece is a black pawn
//        if (pieceToMove.getPieceType() == PieceType.PAWN && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
//            //Do nothing if the pawn is at the end of the board
//            if (myPosition.getRow() == 0) {
//
//            } else {
//                //If there is a white piece to the diagonal left of the pawn
//                ChessPiece diagonalLeftPiece = board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn()));
//                if (myPosition.getColumn() == 0) {
//                    //If the pawn is on the very left of the board
//                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() != 1) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
//                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() == 1){
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() , myPosition.getColumn()), PieceType.QUEEN));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), PieceType.ROOK));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), PieceType.BISHOP));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), PieceType.KNIGHT));
//                }
//                //If there is a white piece to the diagonal right of the pawn
//                ChessPiece diagonalRightPiece = board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2));
//                if (myPosition.getColumn() == 7) {
//                    //If the pawn is on the very right of the board
//                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() != 1) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
//                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() == 1) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() , myPosition.getColumn() + 2), PieceType.QUEEN));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), PieceType.ROOK));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), PieceType.BISHOP));
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), PieceType.KNIGHT));
//                }
//                //If there is a piece in front of the pawn
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null) {
//
//                } else{
//                    //If the pawn is at the second-to-last square
//                    if (myPosition.getRow() == 1) {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() , myPosition.getColumn() + 1), PieceType.QUEEN));
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), PieceType.ROOK));
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), PieceType.BISHOP));
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), PieceType.KNIGHT));
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
//                    }
//                    //If there is a piece two spaces in front of the pawn
//                    if (board.getPiece(new ChessPosition(5, myPosition.getColumn() + 1)) != null) {
//
//                    } else if (myPosition.getRow() == 6) {
//                        //If the pawn hasn't been moved it can go two spaces
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1), null));
//                    }
//                }
//            }
//        }
//
//        //What to do if the piece is a white bishop
//        if (pieceToMove.getPieceType() == PieceType.BISHOP && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
//            //Save original row and column
//            int originalRow = myPosition.getRow();
//            int originalCol = myPosition.getColumn();
//            //Moves to the top right of the bishop
//            int row = originalRow;
//            int col = originalCol;
//            while (row != 7 && col != 7) {
//                row++;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the top left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 7 && col != 0) {
//                row++;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom right of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 7) {
//                row--;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 0) {
//                row--;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//        }
//
//        //What to do if the piece is a black bishop
//        if (pieceToMove.getPieceType() == PieceType.BISHOP && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
//            //Save original row and column
//            int originalRow = myPosition.getRow();
//            int originalCol = myPosition.getColumn();
//            //Moves to the top right of the bishop
//            int row = originalRow;
//            int col = originalCol;
//            while (row != 7 && col != 7) {
//                row++;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the top left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 7 && col != 0) {
//                row++;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom right of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 7) {
//                row--;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 0) {
//                row--;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//        }
//
//        //What to do if the piece is a white king
//        if (pieceToMove.getPieceType() == PieceType.KING && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
//            //Up move
//            if (myPosition.getRow() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
//                }
//            }
//            //Down move
//            if (myPosition.getRow() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
//                }
//            }
//            //Left move
//            if (myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
//                }
//            }
//            //Right move
//            if (myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Up-Right move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Up-Left move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
//                }
//            }
//            //Down-Right move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
//                }
//            }
//            //Down-Left move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
//                }
//            }
//        }
//        //What to do if the piece is a black king
//        if (pieceToMove.getPieceType() == PieceType.KING && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
//            //Up move
//            if (myPosition.getRow() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
//                }
//            }
//            //Down move
//            if (myPosition.getRow() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
//                }
//            }
//            //Left move
//            if (myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
//                }
//            }
//            //Right move
//            if (myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)) != null) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Up-Right move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Up-Left move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
//                }
//            }
//            //Down-Right move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
//                }
//            }
//            //Down-Left move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
//                }
//            }
//        }
//
//        //What to do if the piece is a white knight
//        if (pieceToMove.getPieceType() == PieceType.KNIGHT && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
//            //Up-Right move
//            if (myPosition.getRow() < 6 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Up-Left move
//            if (myPosition.getRow() < 6 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn()), null));
//                }
//            }
//            //Right-Up move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() < 6) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3), null));
//                }
//            }
//            //Right-Down move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() < 6) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3), null));
//                }
//            }
//            //Down-Right move
//            if (myPosition.getRow() > 1 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Down-Left move
//            if (myPosition.getRow() > 1 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()), null));
//                }
//            }
//            //Left-Up move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() > 1) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1), null));
//                }
//            }
//            //Left-Down move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() > 1) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1), null));
//                }
//            }
//        }
//        //What to do if the piece is a black knight
//        if (pieceToMove.getPieceType() == PieceType.KNIGHT && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
//            //Up-Right move
//            if (myPosition.getRow() < 6 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Up-Left move
//            if (myPosition.getRow() < 6 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn()), null));
//                }
//            }
//            //Right-Up move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() < 6) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 3), null));
//                }
//            }
//            //Right-Down move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() < 6) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 3), null));
//                }
//            }
//            //Down-Right move
//            if (myPosition.getRow() > 1 && myPosition.getColumn() != 7) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2), null));
//                }
//            }
//            //Down-Left move
//            if (myPosition.getRow() > 1 && myPosition.getColumn() != 0) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()), null));
//                }
//            }
//            //Left-Up move
//            if (myPosition.getRow() != 7 && myPosition.getColumn() > 1) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1), null));
//                }
//            }
//            //Left-Down move
//            if (myPosition.getRow() != 0 && myPosition.getColumn() > 1) {
//                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)) != null) {
//                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//
//                    } else {
//                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1), null));
//                    }
//                } else {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1), null));
//                }
//            }
//        }
//
//        //What to do if the piece is a white rook
//        if (pieceToMove.getPieceType() == PieceType.ROOK && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
//            //Save original row and column
//            int originalRow = myPosition.getRow();
//            int originalCol = myPosition.getColumn();
//            int row = originalRow;
//            //Up moves
//            while (row < 7) {
//                row++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Down moves
//            row = originalRow;
//            while (row > 0) {
//                row--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            int col = originalCol;
//            //Right moves
//            while (col < 7) {
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            //Left moves
//            col = originalCol;
//            while (col > 0) {
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//        }
//
//        //What to do if the piece is a black rook
//        if (pieceToMove.getPieceType() == PieceType.ROOK && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
//            //Save original row and column
//            int originalRow = myPosition.getRow();
//            int originalCol = myPosition.getColumn();
//            int row = originalRow;
//            //Up moves
//            while (row < 7) {
//                row++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Down moves
//            row = originalRow;
//            while (row > 0) {
//                row--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            int col = originalCol;
//            //Right moves
//            while (col < 7) {
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            //Left moves
//            col = originalCol;
//            while (col > 0) {
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//        }
//
//        //What to do if the piece is a white queen
//        if (pieceToMove.getPieceType() == PieceType.QUEEN && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
//            //Save original row and column
//            int originalRow = myPosition.getRow();
//            int originalCol = myPosition.getColumn();
//            //Moves to the top right of the bishop
//            int row = originalRow;
//            int col = originalCol;
//            while (row != 7 && col != 7) {
//                row++;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the top left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 7 && col != 0) {
//                row++;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom right of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 7) {
//                row--;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 0) {
//                row--;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            row = originalRow;
//            while (row < 7) {
//                row++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Down moves
//            row = originalRow;
//            while (row > 0) {
//                row--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            col = originalCol;
//            //Right moves
//            while (col < 7) {
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            //Left moves
//            col = originalCol;
//            while (col > 0) {
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//        }
//        //What to do if the piece is a black queen
//        if (pieceToMove.getPieceType() == PieceType.QUEEN && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
//            //Save original row and column
//            int originalRow = myPosition.getRow();
//            int originalCol = myPosition.getColumn();
//            //Moves to the top right of the bishop
//            int row = originalRow;
//            int col = originalCol;
//            while (row != 7 && col != 7) {
//                row++;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the top left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 7 && col != 0) {
//                row++;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom right of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 7) {
//                row--;
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Moves to the bottom left of the bishop
//            row = originalRow;
//            col = originalCol;
//            while (row != 0 && col != 0) {
//                row--;
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            row = originalRow;
//            while (row < 7) {
//                row++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//            //Down moves
//            row = originalRow;
//            while (row > 0) {
//                row--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                } else if (board.getPiece(new ChessPosition(row + 1, originalCol + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, originalCol + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            col = originalCol;
//            //Right moves
//            while (col < 7) {
//                col++;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//
//            //Left moves
//            col = originalCol;
//            while (col > 0) {
//                col--;
//                //If there is no piece there
//                if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)) == null) {
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                } else if (board.getPiece(new ChessPosition(originalRow + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
//                    //If there is a black piece there
//                    moveList.add(new ChessMove(myPosition, new ChessPosition(originalRow + 1, col + 1), null));
//                    break;
//                } else {
//                    //If there is a white piece there
//                    break;
//                }
//            }
//        }
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
