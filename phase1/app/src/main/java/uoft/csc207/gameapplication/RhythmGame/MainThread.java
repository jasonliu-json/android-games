package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Hacky way to manage threading and updates.
 */
public class MainThread extends Thread {

    private RhythmGameView rhythmGameView;
    /**
     * The canvas container.
     */
    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the fish tank.
     */

//    public Bitmap bitmap;

    /**
     * Construct the thread.
     *
     */
    public MainThread(RhythmGameView rhythmGameDriver) {
        this.rhythmGameView = rhythmGameDriver;
    }

    @Override
    public void run() {
        while (isRunning) {
//            canvas = null;

            try {
                    this.rhythmGameView.update();
                  rhythmGameView.invalidate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }

            try {
                this.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
