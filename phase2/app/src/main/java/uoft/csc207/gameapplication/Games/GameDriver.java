package uoft.csc207.gameapplication.Games;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

/**
 * Takes in touch inputs for a game.
 * Draws the game to canvas.
 */
public abstract class GameDriver {
    // To draw on
    public Bitmap bitmap;
    public Canvas newCanvas;

    // Screen Dimensions
    public int screenHeight;
    public int screenWidth;

    public void init(DisplayMetrics metrics) {
        screenHeight = metrics.heightPixels - 40;
        screenWidth = metrics.widthPixels;
        bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);
    }

    /**
     * When the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {}

    /**
     * As the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchMove(float x, float y) {}

    /**
     * When the touch is lifted off the screen.
     */
    public void touchUp() {}

    /**
     * Draws the game to canvas.
     * @param canvas the canvas to draw on.
     */
    public abstract void draw(Canvas canvas);

    public abstract boolean getGameIsOver();

    public abstract int getPoints();
}
