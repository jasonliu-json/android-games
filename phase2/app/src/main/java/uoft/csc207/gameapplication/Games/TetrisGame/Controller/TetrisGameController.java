package uoft.csc207.gameapplication.Games.TetrisGame.Controller;

import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameMediator;

import static java.lang.Thread.sleep;

public class TetrisGameController {

    private TetrisGameMediator mediator;
    private DisplayMetrics metrics;
    private int xEnd;
    private int yEnd;
    private int xStart;
    private int yStart;

    public TetrisGameController(TetrisGameMediator mediator, DisplayMetrics metrics) {
        this.mediator = mediator;
        this.metrics = metrics;
    }

    public void touchStart(float x, float y) {
        xEnd = (int) x;
        yEnd = (int) y;
        xStart = (int) x;
        yStart = (int) y;
    }

    public void touchMove(float x, float y) {
        try {
            sleep(40);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            int xDistance = (int) x - xEnd;
            int yDistance = (int) y - yEnd;
            if (xDistance > 20) {
                mediator.moveRight();
            } else if (xDistance < -20) {
                mediator.moveLeft();
            } else if (yDistance > 20) {
                mediator.moveDown();
            }
            this.xEnd = (int) x;
            this.yEnd = (int) y;
        }
    }

    public void touchUp() {
        if (Math.abs(xEnd - xStart) < 10 && Math.abs(yEnd - yStart) < 10) {
            if (xStart < metrics.widthPixels / 2) {
                mediator.rotateCounterClockwise();
            } else {
                mediator.rotateClockwise();
            }
        }
    }
}
