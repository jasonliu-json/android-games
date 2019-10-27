package uoft.csc207.gameapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

public abstract class GameDriver {
    public Bitmap bitmap;

    public Canvas newCanvas;

    public int screenHeight;
    public int screenWidth;

    public void init(DisplayMetrics metrics) {
        screenHeight = metrics.heightPixels - 40;
        screenWidth = metrics.widthPixels;
        bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);
    }

    public abstract void touchStart(float x, float y);

    public abstract void touchMove(float x, float y);

    public abstract void touchUp();

    public abstract void draw(Canvas canvas);

}
