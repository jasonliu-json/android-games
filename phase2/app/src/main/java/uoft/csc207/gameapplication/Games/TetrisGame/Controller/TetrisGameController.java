package uoft.csc207.gameapplication.Games.TetrisGame.Controller;

import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.TetrisGame.Request;

import static java.lang.Thread.sleep;

public class TetrisGameController {

    private DisplayMetrics metrics;
    private int xEnd;
    private int yEnd;
    private int xStart;
    private int yStart;

    public TetrisGameController(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public void touchStart(float x, float y) {
        xEnd = (int) x;
        yEnd = (int) y;
        xStart = (int) x;
        yStart = (int) y;
    }

    public Request touchMove(float x, float y) {
        try {
            sleep(40);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            int xDistance = (int) x - xEnd;
            int yDistance = (int) y - yEnd;
            xEnd = (int) x;
            yEnd = (int) y;
            if (xDistance > 20) {
                return Request.MOVE_RIGHT;
            } else if (xDistance < -20) {
                return Request.MOVE_LEFT;
            } else if (yDistance > 20) {
                return Request.MOVE_DOWN;
            }
            return null;
        }
    }

    public Request touchUp() {
        if (Math.abs(xEnd - xStart) < 10 && Math.abs(yEnd - yStart) < 10) {
            if (yStart > metrics.heightPixels * 0.85) {
                return Request.DROP_DOWN;
            } else if (xStart < metrics.widthPixels * 0.5) {
                return Request.ROTATE_CLOCKWISE;
            } else {
                return Request.ROTATE_COUNTERCLOCKWISE;
            }
        }
        return null;
    }
}
