package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

class Board {

    private int WIDTH = 10;
    private int HEIGHT = 20;

    private char[][] board;

    Board() {
        board = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    char[][] getBoard() {
        return board;
    }

    private boolean rowIsFull(int n) {
        for (int x = 0; x < WIDTH; x++) {
            if (board[n][x] == '.') {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int n) {
        for (int y = n; y > 0; y--) {   // updates rows 1-n
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = board[y - 1][x];
            }
        }
        Arrays.fill(board[0], '.');   // updates row 0
    }

    int clearRows() {
        int score = 0;
        for (int y = 0; y < HEIGHT; y++) {
            if (this.rowIsFull(y)) {
                this.clearRow(y);
                score += 100;
            }
        }
        return score;
    }

    public void drawBoard(Canvas canvas) {
        int i, k;
        int x, y;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        int width = (canvas.getWidth() / 12);
        int height = (canvas.getWidth() / 12);
        for (i = 0; i < HEIGHT; i++) {
            for (k = 0; k < WIDTH; k++) {
                x = k * width;
                y = i * height;
                if (board[i][k] != '.') {
                    Rect rect = new Rect(x, y, x + width, y + width);
                    canvas.drawRect(rect, paint);
                }
            }
        }
    }
}

