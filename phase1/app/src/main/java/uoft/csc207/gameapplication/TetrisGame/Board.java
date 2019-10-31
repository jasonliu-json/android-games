package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;

class Board {

    private int width = 10;
    private int height = 20;

    private char[][] board;

    Board() {
        board = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    char[][] getBoard() {
        return board;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    private boolean rowIsFull(int n) {
        for (int x = 0; x < width; x++) {
            if (board[n][x] == '.') {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int n) {
        for (int y = n; y > 0; y--) {   // updates rows 1-n
            for (int x = 0; x < width; x++) {
                board[y][x] = board[y - 1][x];
            }
        }
        Arrays.fill(board[0], '.');   // updates row 0
    }

    int clearRows() {
        int score = 0;
        for (int y = 0; y < height; y++) {
            if (this.rowIsFull(y)) {
                this.clearRow(y);
                score += 100;
            }
        }
        return score;
    }
}

