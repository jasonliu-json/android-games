package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.GameDriver;


public class RhythmGameDriver extends GameDriver {
    private Paint circlePaint = new Paint();

    private int X;
    private int Y;

    private RhythmGame rhythmGame;

    public RhythmGameDriver() {
        //rhythmGame = new RhythmGame(screenHeight, screenWidth, 4);
        rhythmGame = new RhythmGame(1988, 1080, 4);
//        circlePaint.setColor(Color.BLACK);
//        circlePaint.setStyle(Paint.Style.FILL);
//        circlePaint.setStrokeWidth(10);
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
        newCanvas.save();

        // some sort of draw method here
        rhythmGame.update();
        rhythmGame.draw(newCanvas);
        newCanvas.drawCircle(X, Y, 20, circlePaint);

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }
}
