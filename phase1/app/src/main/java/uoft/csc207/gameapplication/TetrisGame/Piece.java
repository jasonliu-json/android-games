package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

abstract class Piece {

    private int x;
    private int y;
    private int rotation;
    String[][] states;

    Piece() {
        x = 3;   // center piece on x-axis of grid
        y = -1;   // fix piece on top of grid (actual coordinates are out of bounds)
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

    private boolean canMoveTo(BoardV2 board, int adjX, int adjY) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.states[this.rotation][y].charAt(x) != '.') {
                    try {
                        if (board.board[this.y + y + adjY][this.x + x + adjX] != '.') {
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

    private boolean tryMove(BoardV2 board, int adjX, int adjY) {
        if (this.canMoveTo(board, adjX, adjY)) {
            this.x += adjX;
            this.y += adjY;
            return true;
        }
        else {
            return false;
        }
    }

    // CALL THESE METHODS IN THE DRIVER
    // returns true if the move was successful
    boolean moveLeft(BoardV2 board) {
        return this.tryMove(board, -1, 0);
    }

    boolean moveRight(BoardV2 board) {
        return this.tryMove(board, 1, 0);
    }

    boolean moveDown(BoardV2 board) {
        return this.tryMove(board, 0, 1);
    }

    // void drop(Board board) {}   // phase 2

    // abstract void rotateClockwise(Board board);   // phase 2

    // abstract void rotateCounterClockwise(Board board);   // phase 2

    // boolean canRotate(Board board)   // phase 2

    public void drawPiece(Canvas canvas, Bitmap bitmap) {
        Rect rect = new Rect();

        Paint black_paint = new Paint();
        black_paint.setStyle(Paint.Style.FILL);
        black_paint.setColor(Color.BLACK);

        int width = (canvas.getWidth() / 12);
        int height = (canvas.getWidth() / 12);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                x = x * width;
                y = y * height;

                if (this.states[this.rotation][y].charAt(x) != '.') {
                    rect.set(x, y, x + width, y + height);
                    canvas.drawBitmap(bitmap, null, rect, black_paint);
                }
            }
        }
    }
}
