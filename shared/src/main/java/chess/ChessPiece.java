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

        //What to do if the piece is a white pawn
        if (pieceToMove.getPieceType() == PieceType.PAWN && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
            //Do nothing if the pawn is at the end of the board
            if (myPosition.getRow() == 7) {

            } else {
                //If there is a black piece to the diagonal left of the pawn
                ChessPiece diagonalLeftPiece = board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()));
                if (myPosition.getColumn() == 0) {
                    //If the pawn is on the very left of the board
                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() != 6) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() == 6) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.QUEEN));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.ROOK));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.BISHOP));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), PieceType.KNIGHT));
                }
                //If there is a black piece to the diagonal right of the pawn
                ChessPiece diagonalRightPiece = board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2));
                if (myPosition.getColumn() == 7) {
                    //If the pawn is on the very right of the board
                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() != 6) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() == 6) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.QUEEN));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.ROOK));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.BISHOP));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), PieceType.KNIGHT));
                }
                //If there is a piece in front of the pawn
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null) {

                } else{
                    //If the pawn is at the second-to-last square
                    if (myPosition.getRow() == 6) {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.QUEEN));
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.ROOK));
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.BISHOP));
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), PieceType.KNIGHT));
                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
                    }
                    //If there is a piece two spaces in front of the pawn
                    if (board.getPiece(new ChessPosition(4, myPosition.getColumn() + 1)) != null) {

                    } else if (myPosition.getRow() == 1) {
                        //If the pawn hasn't been moved it can go two spaces
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 3, myPosition.getColumn() + 1), null));
                    }
                }
            }
        }

        //What to do if the piece is a black pawn
        if (pieceToMove.getPieceType() == PieceType.PAWN && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
            //Do nothing if the pawn is at the end of the board
            if (myPosition.getRow() == 0) {

            } else {
                //If there is a white piece to the diagonal left of the pawn
                ChessPiece diagonalLeftPiece = board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn()));
                if (myPosition.getColumn() == 0) {
                    //If the pawn is on the very left of the board
                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() != 1) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
                } else if (diagonalLeftPiece != null && diagonalLeftPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() == 1){
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() , myPosition.getColumn()), PieceType.QUEEN));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), PieceType.ROOK));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), PieceType.BISHOP));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), PieceType.KNIGHT));
                }
                //If there is a white piece to the diagonal right of the pawn
                ChessPiece diagonalRightPiece = board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2));
                if (myPosition.getColumn() == 7) {
                    //If the pawn is on the very right of the board
                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() != 1) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
                } else if (diagonalRightPiece != null && diagonalRightPiece.getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() == 1) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() , myPosition.getColumn() + 2), PieceType.QUEEN));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), PieceType.ROOK));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), PieceType.BISHOP));
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), PieceType.KNIGHT));
                }
                //If there is a piece in front of the pawn
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null) {

                } else{
                    //If the pawn is at the second-to-last square
                    if (myPosition.getRow() == 1) {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() , myPosition.getColumn() + 1), PieceType.QUEEN));
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), PieceType.ROOK));
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), PieceType.BISHOP));
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), PieceType.KNIGHT));
                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
                    }
                    //If there is a piece two spaces in front of the pawn
                    if (board.getPiece(new ChessPosition(5, myPosition.getColumn() + 1)) != null) {

                    } else if (myPosition.getRow() == 6) {
                        //If the pawn hasn't been moved it can go two spaces
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1), null));
                    }
                }
            }
        }

        //What to do if the piece is a white bishop
        if (pieceToMove.getPieceType() == PieceType.BISHOP && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
            //Save original row and column
            int originalRow = myPosition.getRow();
            int originalCol = myPosition.getColumn();
            //Moves to the top right of the bishop
            int row = originalRow;
            int col = originalCol;
            while (row != 7 && col != 7) {
                row++;
                col++;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the top left of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 7 && col != 0) {
                row++;
                col--;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the bottom right of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 0 && col != 7) {
                row--;
                col++;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the bottom left of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 0 && col != 0) {
                row--;
                col--;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.BLACK) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
        }

        //What to do if the piece is a black bishop
        if (pieceToMove.getPieceType() == PieceType.BISHOP && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
            //Save original row and column
            int originalRow = myPosition.getRow();
            int originalCol = myPosition.getColumn();
            //Moves to the top right of the bishop
            int row = originalRow;
            int col = originalCol;
            while (row != 7 && col != 7) {
                row++;
                col++;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the top left of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 7 && col != 0) {
                row++;
                col--;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the bottom right of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 0 && col != 7) {
                row--;
                col++;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
            //Moves to the bottom left of the bishop
            row = originalRow;
            col = originalCol;
            while (row != 0 && col != 0) {
                row--;
                col--;
                //If there is no piece there
                if (board.getPiece(new ChessPosition(row + 1, col + 1)) == null) {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                } else if (board.getPiece(new ChessPosition(row + 1, col + 1)).pieceColor == ChessGame.TeamColor.WHITE) {
                    //If there is a black piece there
                    moveList.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                    break;
                } else {
                    //If there is a white piece there
                    break;
                }
            }
        }

        //What to do if the piece is a white king
        if (pieceToMove.getPieceType() == PieceType.KING && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {
            //Up move
            if (myPosition.getRow() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
                }
            }
            //Down move
            if (myPosition.getRow() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
                }
            }
            //Left move
            if (myPosition.getColumn() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
                }
            }
            //Right move
            if (myPosition.getColumn() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
                }
            }
            //Up-Right move
            if (myPosition.getRow() != 7 && myPosition.getColumn() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
                }
            }
            //Up-Left move
            if (myPosition.getRow() != 7 && myPosition.getColumn() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
                }
            }
            //Down-Right move
            if (myPosition.getRow() != 0 && myPosition.getColumn() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
                }
            }
            //Down-Left move
            if (myPosition.getRow() != 0 && myPosition.getColumn() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())).pieceColor == ChessGame.TeamColor.WHITE) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
                }
            }
        }
        //What to do if the piece is a black king
        if (pieceToMove.getPieceType() == PieceType.KING && pieceToMove.pieceColor == ChessGame.TeamColor.BLACK) {
            //Up move
            if (myPosition.getRow() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), null));
                }
            }
            //Down move
            if (myPosition.getRow() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1)).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), null));
                }
            }
            //Left move
            if (myPosition.getColumn() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), null));
                }
            }
            //Right move
            if (myPosition.getColumn() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)) != null) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), null));
                }
            }
            //Up-Right move
            if (myPosition.getRow() != 7 && myPosition.getColumn() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 2), null));
                }
            }
            //Up-Left move
            if (myPosition.getRow() != 7 && myPosition.getColumn() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn()), null));
                }
            }
            //Down-Right move
            if (myPosition.getRow() != 0 && myPosition.getColumn() != 7) {
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2)).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 2), null));
                }
            }
            //Down-Left move
            if (myPosition.getRow() != 0 && myPosition.getColumn() != 0) {
                if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())) != null) {
                    if (board.getPiece(new ChessPosition(myPosition.getRow(), myPosition.getColumn())).pieceColor == ChessGame.TeamColor.BLACK) {

                    } else {
                        moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
                    }
                } else {
                    moveList.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(), myPosition.getColumn()), null));
                }
            }
        }

        //What to do if the piece is a white knight
        if (pieceToMove.getPieceType() == PieceType.KNIGHT && pieceToMove.pieceColor == ChessGame.TeamColor.WHITE) {

        }
        return moveList;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }
}
