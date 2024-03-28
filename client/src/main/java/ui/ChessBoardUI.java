package ui;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static ui.EscapeSequences.*;

public class ChessBoardUI {

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final ChessPiece[][] board = new ChessPiece[BOARD_SIZE_IN_SQUARES][BOARD_SIZE_IN_SQUARES];
    private static boolean[][] highlightedSquares = new boolean[BOARD_SIZE_IN_SQUARES][BOARD_SIZE_IN_SQUARES];
    public static void printBoard(ChessGame game, ChessGame.TeamColor teamColor, ChessPosition position) {
        for (int row = 1; row <= BOARD_SIZE_IN_SQUARES; row++) {
            for (int col = 1; col <= BOARD_SIZE_IN_SQUARES; col++) {
                board[row - 1][col - 1] = game.getBoard().getPiece(new ChessPosition(row, col));
            }
        }

        for (int row = 1; row <= BOARD_SIZE_IN_SQUARES; row++) {
            for (int col = 1; col <= BOARD_SIZE_IN_SQUARES; col++) {
                highlightedSquares[row - 1][col - 1] = false;
            }
        }

        if (position != null) {
            Collection<ChessMove> validMoves = game.validMoves(position);
            for (ChessMove move : validMoves) {
                highlightedSquares[move.endPosition().getRow()][move.endPosition().getColumn()] = true;
            }
        }

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        if (teamColor == ChessGame.TeamColor.BLACK) {
            drawBlackChessBoard(out);
        } else {
            drawWhiteChessBoard(out);
        }

        out.print(RESET_BG_COLOR);
        out.println();
    }
    private static void drawBlackChessBoard(PrintStream out) {
        drawEndRow(out, 2);
        drawMiddleRows(out, 2);
        drawEndRow(out, 2);
    }
    private static void drawWhiteChessBoard(PrintStream out) {
        drawEndRow(out, 1);
        drawMiddleRows(out, 1);
        drawEndRow(out, 1);
    }
    private static void drawEndRow(PrintStream out, int color) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print("   ");

        if (color == 2) {
            for (int i = 0; i < BOARD_SIZE_IN_SQUARES; i++) {
                out.print(" " + (char) ('h' - i) + " ");
            }
        } else {
            for (int i = 0; i < BOARD_SIZE_IN_SQUARES; i++) {
                out.print(" " + (char) ('a' + i) + " ");
            }
        }

        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.println();
    }
    private static void drawMiddleRows(PrintStream out, int color) {
        for (int row = 0; row < BOARD_SIZE_IN_SQUARES; row++) {
            drawRow(out, row, color);
        }
    }
    private static void drawRow(PrintStream out, int row, int color) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        if (color == 1) {
            out.print(" " + (char) ('8' - row) + " ");
        } else {
            out.print(" " + (char) ('1' + row) + " ");
        }
        for (int col = 0; col < BOARD_SIZE_IN_SQUARES; col++) {
            drawSquare(out, row, col, color);
        }
        out.print(RESET_TEXT_BOLD_FAINT);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        if (color == 1) {
            out.print(" " + (char) ('8' - row) + " ");
        } else {
            out.print(" " + (char) ('1' + row) + " ");
        }
        out.print(RESET_BG_COLOR);
        out.println();
    }
    private static char getLetter(ChessPiece piece) {
        if (piece == null) {
            return 0;
        }
        return switch (piece.getPieceType()) {
            case KING -> 'K';
            case QUEEN -> 'Q';
            case ROOK -> 'R';
            case BISHOP -> 'B';
            case KNIGHT -> 'N';
            case PAWN -> 'P';
        };
    }
    private static void drawSquare(PrintStream out, int row, int col, int color) {
        if (color == 1 && highlightedSquares[7 - row][col]) {
            out.print(SET_BG_COLOR_YELLOW);
        } else if (color == 2 && highlightedSquares[row][7 - col]) {
            out.print(SET_BG_COLOR_YELLOW);
        } else if ((row + col) % 2 == 0) {
            out.print(SET_BG_COLOR_WHITE);
        } else {
            out.print(SET_BG_COLOR_BLACK);
        }

        char letter;

        if (color == 2) {
            letter = getLetter(board[row][col]);
        } else {
            letter = getLetter(board[7 - row][7 - col]);
        }

        if (color == 2) {
            if (board[row][col] != null && board[row][col].getTeamColor() == ChessGame.TeamColor.BLACK) {
                out.print(SET_TEXT_COLOR_RED);
            } else {
                out.print(SET_TEXT_COLOR_BLUE);
            }
        } else {
            if (board[7 - row][7 - col] != null && board[7 - row][7 - col].getTeamColor() == ChessGame.TeamColor.BLACK) {
                out.print(SET_TEXT_COLOR_RED);
            } else {
                out.print(SET_TEXT_COLOR_BLUE);
            }
        }


        out.print(SET_TEXT_BOLD);

        if (letter == 0) {
            out.print("   ");
        } else {
            out.print(" " + letter + " ");
        }
    }
}