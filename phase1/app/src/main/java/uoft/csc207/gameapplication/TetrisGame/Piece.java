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
                if (states[rotation][y].charAt(x) != '.') {
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
        this.removePieceFromBoard(board);
        if (canMoveTo(board, adjX, adjY)) {
            this.x += adjX;
            this.y += adjY;
            addPieceToBoard(board);
            return true;
        }
        addPieceToBoard(board);
        return false;
    }

    void addPieceToBoard(BoardV2 board) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[rotation][y].charAt(x) != '.') {
                    board.getBoard()[this.y + y][this.x + x] = states[rotation][y].charAt(x);
                }
            }
        }
    }

    void removePieceFromBoard(BoardV2 board) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[rotation][y].charAt(x) != '.') {
                    board.getBoard()[this.y + y][this.x + x] = '.';
                }
            }
        }
    }

    void moveLeft(BoardV2 board) {
        tryMove(board, -1, 0);
    }

    void moveRight(BoardV2 board) {
        tryMove(board, 1, 0);
    }

    boolean moveDown(BoardV2 board) {
        return tryMove(board, 0, 1);
    }

    void dropDown(BoardV2 board) {
        while (tryMove(board, 0, 1)) {
            moveDown(board);
        }
    }

    private boolean canRotate(BoardV2 board, int direction) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[(rotation + direction) % states.length][y].charAt(x) != '.') {
                    try {
                        if (board.board[this.y + y][this.x + x] != '.') {
                            return false;   // rotation results in collision
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;   // rotation is out of bounds
                    }
                }
            }
        }
        return true;
    }

    private void tryRotate(BoardV2 board, int direction) {
        removePieceFromBoard(board);
        if (canRotate(board, direction)) {
            rotation = (rotation + direction) % states.length;
        }
        addPieceToBoard(board);
    }

    void rotateClockwise(BoardV2 board) {
        tryRotate(board, 1);
    }

    void rotateCounterClockwise(BoardV2 board) {
        tryRotate(board, -1);
    }

//    public void addPiece(Canvas canvas, Bitmap bitmap) {
//        Rect rect = new Rect();
//
//        Paint black_paint = new Paint();
//        black_paint.setStyle(Paint.Style.FILL);
//        black_paint.setColor(Color.BLACK);
//
//        int width = (canvas.getWidth() / 12);
//        int height = (canvas.getWidth() / 12);
//
//        for (int y = 0; y < 5; y++) {
//            for (int x = 0; x < 5; x++) {
//                x = x * width;
//                y = y * height;
//
//                if (this.states[this.rotation][y].charAt(x) != '.') {
//                    rect.set(x, y, x + width, y + height);
//                    canvas.drawBitmap(bitmap, null, rect, black_paint);
//                }
//            }
//        }
//    }
}
