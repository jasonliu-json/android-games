package uoft.csc207.gameapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Hacky way to manage threading and updates. Taken from FishTank Assignment
 */
public class MainThread extends Thread {

    /**
     * Where the game items are drawn.
     */
    private GameView gameView;
    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;
    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the game.
     */
    public static Canvas canvas;

    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param view          where the game is displayed and drawn.
     */
    public MainThread(SurfaceHolder surfaceHolder, GameView view) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = view;
    }

    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                this.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
