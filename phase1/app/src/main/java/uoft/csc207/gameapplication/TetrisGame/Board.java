package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Board {
    private int[][] b;

    public Board() {
        this.b = new int[20][10];
    }

    public void drawBoard(Canvas canvas, Bitmap bitmap) {
        Rect rect = new Rect();

        int i, j;
        int x, y;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        int width = (canvas.getWidth() / 12);
        int height = (canvas.getWidth() / 12); // since they are little squares
        for (i = 0; i < 20; i++) {
            for (j = 0; j < 10; j++) {
                x = j * width;
                y = i * height;
                if (b[i][j] == 1) {
                    rect.set(x, y, x + width, y + height);
                    canvas.drawBitmap(bitmap, null, rect, paint);
                }
            }
        }
    }

    public boolean gameOver() {
        for (int i = 0; i < 10; i++) {
            if (b[0][i] == 1) {
                return true;
            }
        }
        return false;
    }


    public boolean is_fill(int line) {
        for (int i = 0; i < 10; i++) {
            if (b[line][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void delete() {
        for (int i = 0; i < 20; i++) {
            if (!is_fill(i)) {
                break;
            } else {
                for (int l = i; l > 0; l--) {
                    for (int j = 0; j < 10; j++) {
                        b[l][j] = b[l - 1][j];
                    }
                }
            }
        }

    }
    // well I have to specify that this is the simplest edition of deletion.


}

