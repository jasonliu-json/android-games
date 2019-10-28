package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

/**
 * This class currently relies on passing in a 10 x 22 array (+2 extra to allow pieces to 'fall in')
 * to detect collisions with other pieces and test boundaries.
 * I am using the array to store information about the location of each filled 'tile' that we can
 * use later to draw 'blocks' onto our activity.
 * <p>
 * Since this is inefficient, I will modify the implementation later so that we can get rid of the
 * 2D array.
 */
abstract class Piece {

    private int x;
    private int y;
    private int rotation;
    Color color;
    String[][] shape;

    Piece() {
        x = 3;   // these coordinates center the pieces on the grid
        y = 0;
        rotation = 0;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getRotation() {
        return rotation;
    }

    private boolean canMoveTo(BoardVersionWithPieceClass board, int adjX, int adjY) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.shape[this.rotation][y].charAt(x) == 'X') {
                    try {
                        if (board.board[this.y + y + adjY][this.x + x + adjX] != null) {
                            return false;   // move results in collision
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;   // move is out of bounds
                    }
                }
            }
        }
        return true;
    }

    private void tryMove(BoardVersionWithPieceClass board, int adjX, int adjY) {
        if (this.canMoveTo(board, adjX, adjY)) {
            this.x += adjX;
            this.y += adjY;
        }
    }

    void moveLeft(BoardVersionWithPieceClass board) {
        this.tryMove(board, -1, 0);
    }

    void moveRight(BoardVersionWithPieceClass board) {
        this.tryMove(board, 1, 0);
    }

    void moveDown(BoardVersionWithPieceClass board) {
        this.tryMove(board, 0, 1);
    }

    // void drop(Board board) {}   // phase 2

    // abstract void rotateClockwise(Board board);   // phase 2 ~ something with modulo

    // abstract void rotateCounterClockwise(Board board);   // phase 2

    // boolean canRotate(Board board)   // phase 2
}
