package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;


public class RhythmGameDriver {
    private Bitmap bitmap;

    private Canvas newCanvas;

    private Paint circlePaint = new Paint();

    private int screenHeight;
    private int screenWidth;

    private int X;
    private int Y;

    private RhythmGame rhythmGame;

    public RhythmGameDriver() {
        rhythmGame = new RhythmGame();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);

    }

    public void init(DisplayMetrics metrics) {
        screenHeight = metrics.heightPixels - 40;
        screenWidth = metrics.widthPixels;
        bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);
    }

    public void touchStart(float x, float y) {
        // Do something to detect Touch given x, y pos
    }

    public void touchMove(float x, float y) {
        // Do something when you cursor is moving around to x, y pos
        X = (int) x;
        Y = (int) y;
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();

        // some sort of draw method here
        newCanvas.drawCircle(X, Y, 20, circlePaint);

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }
}
