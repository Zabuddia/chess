package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static ui.EscapeSequences.*;

public class ChessBoardUI {

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final char[][][] board = new char[BOARD_SIZE_IN_SQUARES][BOARD_SIZE_IN_SQUARES][2];
    public static void main(String[] args) {
        board[0][0][0] = 'R';
        board[0][0][1] = 'B';
        board[0][1][0] = 'N';
        board[0][1][1] = 'B';
        board[0][2][0] = 'B';
        board[0][2][1] = 'B';
        board[0][3][0] = 'Q';
        board[0][3][1] = 'B';
        board[0][4][0] = 'K';
        board[0][4][1] = 'B';
        board[0][5][0] = 'B';
        board[0][5][1] = 'B';
        board[0][6][0] = 'N';
        board[0][6][1] = 'B';
        board[0][7][0] = 'R';
        board[0][7][1] = 'B';
        board[1][0][0] = 'P';
        board[1][0][1] = 'B';
        board[1][1][0] = 'P';
        board[1][1][1] = 'B';
        board[1][2][0] = 'P';
        board[1][2][1] = 'B';
        board[1][3][0] = 'P';
        board[1][3][1] = 'B';
        board[1][4][0] = 'P';
        board[1][4][1] = 'B';
        board[1][5][0] = 'P';
        board[1][5][1] = 'B';
        board[1][6][0] = 'P';
        board[1][6][1] = 'B';
        board[1][7][0] = 'P';
        board[1][7][1] = 'B';

        board[7][0][0] = 'R';
        board[7][0][1] = 'W';
        board[7][1][0] = 'N';
        board[7][1][1] = 'W';
        board[7][2][0] = 'B';
        board[7][2][1] = 'W';
        board[7][3][0] = 'Q';
        board[7][3][1] = 'W';
        board[7][4][0] = 'K';
        board[7][4][1] = 'W';
        board[7][5][0] = 'B';
        board[7][5][1] = 'W';
        board[7][6][0] = 'N';
        board[7][6][1] = 'W';
        board[7][7][0] = 'R';
        board[7][7][1] = 'W';
        board[6][0][0] = 'P';
        board[6][0][1] = 'W';
        board[6][1][0] = 'P';
        board[6][1][1] = 'W';
        board[6][2][0] = 'P';
        board[6][2][1] = 'W';
        board[6][3][0] = 'P';
        board[6][3][1] = 'W';
        board[6][4][0] = 'P';
        board[6][4][1] = 'W';
        board[6][5][0] = 'P';
        board[6][5][1] = 'W';
        board[6][6][0] = 'P';
        board[6][6][1] = 'W';
        board[6][7][0] = 'P';
        board[6][7][1] = 'W';

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawWhiteChessBoard(out);

        out.print(RESET_BG_COLOR);
        out.println();

        drawBlackChessBoard(out);

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
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
    private static void drawSquare(PrintStream out, int row, int col, int color) {
        if ((row + col) % 2 == 0) {
            out.print(SET_BG_COLOR_WHITE);
        } else {
            out.print(SET_BG_COLOR_BLACK);
        }

        char letter;

        if (color == 2) {
            letter = board[row][col][0];
        } else {
            letter = board[7 - row][7 - col][0];
        }

        if (color == 2) {
            if (board[row][col][1] == 'B') {
                out.print(SET_TEXT_COLOR_BLUE);
            } else {
                out.print(SET_TEXT_COLOR_RED);
            }
        } else {
            if (board[7 - row][7 - col][1] == 'B') {
                out.print(SET_TEXT_COLOR_BLUE);
            } else {
                out.print(SET_TEXT_COLOR_RED);
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
