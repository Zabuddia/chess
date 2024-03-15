package ui;

import chess.ChessGame;
import chess.ChessPiece;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static ui.EscapeSequences.*;

public class ChessBoardUI {

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final ChessPiece[][] board = new ChessPiece[BOARD_SIZE_IN_SQUARES][BOARD_SIZE_IN_SQUARES];
    public static void printBoard() {
        board[0][0] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        board[0][1] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        board[0][2] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        board[0][3] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        board[0][4] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        board[0][5] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        board[0][6] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        board[0][7] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        board[1][0] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][1] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][2] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][3] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][4] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][5] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][6] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        board[1][7] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

        board[7][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        board[7][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        board[7][2] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        board[7][3] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        board[7][4] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        board[7][5] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        board[7][6] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        board[7][7] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        board[6][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][2] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][3] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][4] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][5] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][6] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[6][7] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawWhiteChessBoard(out);

        out.print(RESET_BG_COLOR);
        out.println();

        drawBlackChessBoard(out);

        out.print(RESET_BG_COLOR);
    }
    private static void drawWhiteChessBoard(PrintStream out) {
        drawEndRow(out, 2);
        drawMiddleRows(out, 2);
        drawEndRow(out, 2);
    }
    private static void drawBlackChessBoard(PrintStream out) {
        drawEndRow(out, 1);
        drawMiddleRows(out, 1);
        drawEndRow(out, 1);
    }
    private static void drawEndRow(PrintStream out, int color) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print("   ");

        if (color == 1) {
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
        if (color == 2) {
            out.print(" " + (char) ('8' - row) + " ");
        } else {
            out.print(" " + (char) ('1' + row) + " ");
        }
        for (int col = 0; col < BOARD_SIZE_IN_SQUARES; col++) {
            drawSquare(out, row, col, color);
        }
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        if (color == 2) {
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
        if ((row + col) % 2 == 0) {
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
            if (board[row][col] != null && board[7 - row][7 - col].getTeamColor() == ChessGame.TeamColor.BLACK) {
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