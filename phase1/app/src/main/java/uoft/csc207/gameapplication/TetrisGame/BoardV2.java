package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;

class BoardV2 {

    private int WIDTH = 10;
    private int HEIGHT = 20;

    char[][] board;   // not private

    BoardV2() {
        board = new char[WIDTH][HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    void addPiece(Piece piece) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                char entry = piece.states[piece.getRotation()][y].charAt(x);
                if (entry != '.') {
                    board[piece.getY() + y][piece.getX() + x] = entry;
                }
            }
        }
    }

    void removePiece(Piece piece) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                char entry = piece.states[piece.getRotation()][y].charAt(x);
                if (entry != '.') {
                    board[piece.getY() + y][piece.getX() + x] = '.';
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

