package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.GameDriver;

public class TetrisGameDriver extends GameDriver {
    private Paint circlePaint = new Paint();

    private int X;
    private int Y;

    public TetrisGameDriver() {
//        rhythmGame = new RhythmGame(screenHeight, screenWidth, 4);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);
    }

    public void touchStart(float x, float y) {
        // Do something to detect Touch given x, y pos
        X = (int) x;
        Y = (int) y;
    }

    public void touchMove(float x, float y) {
        // Do something when you cursor is moving around to x, y pos
        //X = (int) x;
        Y = (int) y;
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
//        newCanvas.save();
//        // some sort of draw method here
//        newCanvas.drawCircle(X, Y, 20, circlePaint);
//        canvas.drawBitmap(bitmap, 0, 0, null);
//        newCanvas.restore();
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
//I have to add the board draw and piece draw here later
    }
}


