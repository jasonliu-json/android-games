package uoft.csc207.gameapplication.Games;

import android.content.Context;
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

    private DisplayMetrics metrics;
    private Context context;
    private String configurations;

    public void init(DisplayMetrics metrics) {
        screenHeight = metrics.heightPixels - 40;
        screenWidth = metrics.widthPixels;
        bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);
    }

    /**
     * Called when the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {}

    /**
     * Called as the touch moves around, still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchMove(float x, float y) {}

    /**
     * Called when the touch is lifted off the screen.
     */
    public void touchUp() {}

    /**
     * Updates the games by one unit time
     */
    public void timeUpdate(){}

    /**
     * Starts thread-related processes
     */
    public void start() {}

    /**
     * Ends thread-related processes
     */
    public void stop() {}

    /**
     * Draws the game to canvas.
     * @param canvas the canvas to draw on.
     */
    public abstract void draw(Canvas canvas);

    public abstract boolean getGameIsOver();

    public abstract int getPoints();

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getConfigurations() {
        return configurations;
    }

    public void setConfigurations(String configurations) {
        this.configurations = configurations;
    }
}
