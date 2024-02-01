package chess;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor teamTurn = TeamColor.WHITE;
    private ChessBoard board = new ChessBoard();
    private ChessMove previousMove = null;
    private boolean enPassantMoveValid = false;

    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> validMoveList = new ArrayList<>();

        ChessPiece piece = board.getPiece(startPosition);

        if (piece != null) {
            validMoveList.addAll(piece.pieceMoves(board, startPosition));
        } else {
            return validMoveList;
        }

        ChessGame.TeamColor pieceColor = piece.getTeamColor();

        //Castling
        if (piece.getPieceType() == ChessPiece.PieceType.KING && !board.isKingMoved(pieceColor)) {
            if (board.isRookQueensideThere(pieceColor) && (!board.isRookQueensideMoved(pieceColor)) && board.castleQueensideSpacesEmpty(pieceColor)) {
                ChessPosition endPosition = new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1);
                ChessMove castleMove = new ChessMove(startPosition, endPosition, null);
                if (!board.isRookQueensideInDanger(castleMove)) {
                    validMoveList.add(castleMove);
                }
            }
            if (board.isRookKingsideThere(pieceColor) && (!board.isRookKingsideMoved(pieceColor)) && board.castleKingsideSpacesEmpty(pieceColor)) {
                ChessPosition endPosition = new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 3);
                ChessMove castleMove = new ChessMove(startPosition, endPosition, null);
                if (!board.isRookKingsideInDanger(castleMove)) {
                    validMoveList.add(castleMove);
                }
            }
        }

        if (pieceColor == TeamColor.WHITE) {
            //En Passant white left
            if (previousMove != null) {
                ChessPiece pieceInPreviousMove = board.getPiece(previousMove.endPosition());
                int previousMoveCol = previousMove.endPosition().getColumn();
                int differenceBetweenPreviousMoveRows = Math.abs(previousMove.endPosition().getRow() - previousMove.startPosition().getRow());
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getTeamColor() != pieceColor && differenceBetweenPreviousMoveRows == 2 && previousMoveCol == startPosition.getColumn() - 1) {
                    ChessPosition endPosition = new ChessPosition(previousMove.endPosition().getRow() + 2, previousMove.endPosition().getColumn() + 1);
                    ChessMove enPassantMove = new ChessMove(startPosition, endPosition, null);
                    validMoveList.add(enPassantMove);
                    enPassantMoveValid = true;
                } else {
                    enPassantMoveValid = false;
                }
            }

            //En Passant white right
            if (previousMove != null) {
                ChessPiece pieceInPreviousMove = board.getPiece(previousMove.endPosition());
                int previousMoveCol = previousMove.endPosition().getColumn();
                int differenceBetweenPreviousMoveRows = Math.abs(previousMove.endPosition().getRow() - previousMove.startPosition().getRow());
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getTeamColor() != pieceColor && differenceBetweenPreviousMoveRows == 2 && previousMoveCol == startPosition.getColumn() + 1) {
                    ChessPosition endPosition = new ChessPosition(previousMove.endPosition().getRow() + 2, previousMove.endPosition().getColumn() + 1);
                    ChessMove enPassantMove = new ChessMove(startPosition, endPosition, null);
                    validMoveList.add(enPassantMove);
                    enPassantMoveValid = true;
                } else {
                    enPassantMoveValid = false;
                }
            }
        } else {
            //En Passant black left
            if (previousMove != null) {
                ChessPiece pieceInPreviousMove = board.getPiece(previousMove.endPosition());
                int previousMoveCol = previousMove.endPosition().getColumn();
                int differenceBetweenPreviousMoveRows = Math.abs(previousMove.endPosition().getRow() - previousMove.startPosition().getRow());
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getTeamColor() != pieceColor && differenceBetweenPreviousMoveRows == 2 && previousMoveCol == startPosition.getColumn() - 1) {
                    ChessPosition endPosition = new ChessPosition(previousMove.endPosition().getRow(), previousMove.endPosition().getColumn() + 1);
                    ChessMove enPassantMove = new ChessMove(startPosition, endPosition, null);
                    validMoveList.add(enPassantMove);
                    enPassantMoveValid = true;
                } else {
                    enPassantMoveValid = false;
                }
            }

            //En Passant black right
            if (previousMove != null) {
                ChessPiece pieceInPreviousMove = board.getPiece(previousMove.endPosition());
                int previousMoveCol = previousMove.endPosition().getColumn();
                int differenceBetweenPreviousMoveRows = Math.abs(previousMove.endPosition().getRow() - previousMove.startPosition().getRow());
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getPieceType() == ChessPiece.PieceType.PAWN && pieceInPreviousMove.getTeamColor() != pieceColor && differenceBetweenPreviousMoveRows == 2 && previousMoveCol == startPosition.getColumn() + 1) {
                    ChessPosition endPosition = new ChessPosition(previousMove.endPosition().getRow(), previousMove.endPosition().getColumn() + 1);
                    ChessMove enPassantMove = new ChessMove(startPosition, endPosition, null);
                    validMoveList.add(enPassantMove);
                    enPassantMoveValid = true;
                } else {
                    enPassantMoveValid = false;
                }
            }
        }


        Collection<ChessMove> invalidMoveList = new ArrayList<>();

        for (ChessMove move : validMoveList) {
            ChessPosition endPosition = move.endPosition();
            ChessPiece savedPiece = board.getPiece(endPosition);
            board.removePiece(startPosition);
            board.addPiece(endPosition, piece);
            if (isInCheck(pieceColor)) {
                board.addPiece(startPosition, piece);
                board.removePiece(endPosition);
                board.addPiece(endPosition, savedPiece);
                invalidMoveList.add(move);
                continue;
            }
            board.addPiece(startPosition, piece);
            board.removePiece(endPosition);
            board.addPiece(endPosition, savedPiece);
        }
        validMoveList.removeAll(invalidMoveList);
        return validMoveList;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPosition = move.startPosition();
        ChessPosition endPosition = move.endPosition();
        ChessPiece piece = board.getPiece(startPosition);
        ChessPiece.PieceType promotion = move.promotionPiece();

        //Invalid if it is not the team's turn
        if (piece.getTeamColor() != teamTurn) {
            throw new InvalidMoveException();
        }

        //Invalid if it is not in the validMoves list
        if (validMoves(startPosition).contains(move)) {
            board.removePiece(startPosition);
            board.addPiece(endPosition, piece);
            if (promotion != null) {
                piece.setPieceType(promotion);
            }
        } else {
            throw new InvalidMoveException();
        }

        if (piece.getPieceType() == ChessPiece.PieceType.KING) {
            board.setKingMoved(teamTurn);
        }

        if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
            board.setRookMoved(startPosition, teamTurn);
        }

        //Castling extra move
        int differenceBetweenCols = Math.abs(endPosition.getColumn() - startPosition.getColumn());
        if (piece.getPieceType() == ChessPiece.PieceType.KING && differenceBetweenCols == 2) {
            board.castleRook(teamTurn, endPosition);
        }

        //En Passant extra move
        if (enPassantMoveValid) {
            if (teamTurn == TeamColor.WHITE) {
                ChessPosition behindPosition = new ChessPosition(endPosition.getRow(), endPosition.getColumn() + 1);
                ChessPiece behindPiece = board.getPiece(behindPosition);
                if (behindPiece != null && behindPiece.getPieceType() == ChessPiece.PieceType.PAWN && differenceBetweenCols == 1 && behindPiece.getPieceType() == ChessPiece.PieceType.PAWN && behindPiece.getTeamColor() != teamTurn) {
                    board.removePiece(behindPosition);
                }
            } else {
                ChessPosition behindPosition = new ChessPosition(endPosition.getRow() + 2, endPosition.getColumn() + 1);
                ChessPiece behindPiece = board.getPiece(behindPosition);
                if (behindPiece != null && behindPiece.getPieceType() == ChessPiece.PieceType.PAWN && differenceBetweenCols == 1 && behindPiece.getPieceType() == ChessPiece.PieceType.PAWN && behindPiece.getTeamColor() != teamTurn) {
                    board.removePiece(behindPosition);
                }
            }
        }



        if (getTeamTurn().equals(TeamColor.WHITE)) {
            setTeamTurn(TeamColor.BLACK);
        } else {
            setTeamTurn(TeamColor.WHITE);
        }

        previousMove = move;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = board.getKingPosition(teamColor);
        TeamColor opponentColor = (teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
        Collection<ChessPosition> occupiedPositionsList = board.occupiedPositionsOfAColor(opponentColor);

        for (ChessPosition occupiedPosition : occupiedPositionsList) {
            ChessPiece piece = board.getPiece(occupiedPosition);
            for (ChessMove move : piece.pieceMoves(board, occupiedPosition)) {
                if (move.endPosition().equals(kingPosition)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean noPossibleMovesCheck(TeamColor teamColor) {
        Collection<ChessPosition> occupiedPositionsList = board.occupiedPositionsOfAColor(teamColor);
        ChessBoard originalBoard = board;

        for (ChessPosition occupiedPosition : occupiedPositionsList) {
            Collection<ChessMove> validMoveList = validMoves(occupiedPosition);
            for (ChessMove move : validMoveList) {
                ChessBoard boardCopy = new ChessBoard(originalBoard);
                ChessPosition startPosition = move.startPosition();
                ChessPosition endPosition = move.endPosition();
                ChessPiece piece = boardCopy.getPiece(startPosition);

                boardCopy.removePiece(startPosition);
                boardCopy.addPiece(endPosition, piece);
                setBoard(boardCopy);
                if (!isInCheck(teamColor)) {
                    return false;
                }
                setBoard(originalBoard);
            }
        }
        return true;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            return false;
        }
        return noPossibleMovesCheck(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }
        return noPossibleMovesCheck(teamColor);
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
