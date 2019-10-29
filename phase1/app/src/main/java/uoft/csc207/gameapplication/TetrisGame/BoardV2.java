package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

class BoardV2 {

    private int WIDTH = 10;
    private int HEIGHT = 20;

    char[][] board;

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

    boolean clearRows() {
        boolean rowsCleared = false;
        for (int y = 0; y < HEIGHT; y++) {
            if (this.rowIsFull(y)) {
                this.clearRow(y);
                rowsCleared = true;
            }
        }
        return rowsCleared;
    }
    public void drawBoard(Canvas canvas) {
        float width = canvas.getWidth() / 12;
        float height = canvas.getWidth() / 12;
        int i, j;
        float x, y;

        x = 0;
        y = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        for (i = 1; i <= 11; i++) {
            canvas.drawLine(x, 0, x, width * 20, paint);
            x = x + width;
        }
        for (j = 1; j <= 21; j++) {
            canvas.drawLine(0, y, height * 10, y, paint);
            y = y + height;
        }
    }
}

