package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;

class BoardV2 {

    private int WIDTH = 10;
    private int HEIGHT = 22;

    char[][] board;   // not private because this is passed around to be modified frequently

    BoardV2() {
        board = new char[WIDTH][HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    void drawPieceOnBoard(Piece piece) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (piece.shape[piece.getRotation()][y].charAt(x) != '.') {
                    board[piece.getY() + y][piece.getX() + x] = piece.shape[piece.getRotation()][y].charAt(x);
                }
            }
        }
    }

    private boolean rowIsFull(int n) {
        for (int x = 0; x < WIDTH; x++) {
            if (board[n][x] == '.') {
                return false;
            }
        }
        return true;
    }

    void clearRow(int n) {
        for (int y = n; y > 0; y--) {   // updates rows 1-n
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = board[y - 1][x];
            }
        }
        Arrays.fill(board[0], '.');   // updates row 0
    }

    void clearRows() {
        for (int y = 0; y < HEIGHT; y++) {
            if (this.rowIsFull(y)) {
                this.clearRow(y);
            }
        }
    }


}

