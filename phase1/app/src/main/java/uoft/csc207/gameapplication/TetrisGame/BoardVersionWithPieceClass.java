package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

import java.util.Arrays;

class BoardVersionWithPieceClass {

    // I originally passed in constants WIDTH = 10, HEIGHT = 22 to the constructor =
    // The extra +2 was to let pieces 'fall' into the board (i.e. we wouldn't visualize these extra
    // rows but they would be where the pieces are generated onto the board)
    // I needed this because the coordinates of each piece is the top-left corner of the 5x5 string array
    // .....
    // .....
    // XXXX.
    // .....
    // .....
    // So the XXXX would spawn at the top of the screen, but the actual coordinates of the piece
    // would be out of the screen
    private int width;
    private int height;

    Color[][] board;   // not private because this is passed around to be modified frequently

    BoardVersionWithPieceClass(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Color[width][height];
    }

    void drawPieceOnBoard(Piece piece) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (piece.shape[piece.getRotation()][y].charAt(x) == 'X') {
                    board[piece.getY() + y][piece.getX() + x] = piece.color;
                }
            }
        }
    }

    private boolean rowIsFull(int n) {
        for (int x = 0; x < width; x++) {
            if (board[n][x] == null) {
                return false;
            }
        }
        return true;
    }

    void clearRow(int n) {
        for (int y = n; y > 0; y--) {   // updates rows 1-n
            for (int x = 0; x < width; x++) {
                board[y][x] = board[y - 1][x];
            }
        }
        Arrays.fill(board[0], null);   // updates row 0
    }

    void clearRows() {
        for (int y = 0; y < height; y++) {
            if (this.rowIsFull(y)) {
                this.clearRow(y);
            }
        }
    }


}

