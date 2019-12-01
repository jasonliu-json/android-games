package uoft.csc207.gameapplication.Games.TetrisGame.Controller;

import static java.lang.Thread.sleep;

public class TetrisGameController {

    private int screenWidth;
    private int screenHeight;
    private int xEnd;
    private int yEnd;
    private int xStart;
    private int yStart;

    public TetrisGameController(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
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
            if (yStart > screenHeight * 0.85) {
                return Request.DROP_DOWN;
            } else if (xStart < screenWidth * 0.5) {
                return Request.ROTATE_CLOCKWISE;
            } else {
                return Request.ROTATE_COUNTERCLOCKWISE;
            }
        }
        return null;
    }
}
