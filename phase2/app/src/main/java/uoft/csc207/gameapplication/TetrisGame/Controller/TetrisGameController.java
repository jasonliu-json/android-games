package uoft.csc207.gameapplication.TetrisGame.Controller;

import uoft.csc207.gameapplication.TetrisGame.GameLogic.TetrisGame;

import static java.lang.Thread.sleep;

public class TetrisGameController {

    private TetrisGame tetrisGame;
    private int xEnd;
    private int yEnd;
    private int xStart;
    private int yStart;

    public TetrisGameController(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
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
            if (!tetrisGame.getGameIsOver()) {
                int xDistance = (int) x - xEnd;
                int yDistance = (int) y - yEnd;
                if (xDistance > 20) {
                    tetrisGame.moveRight();
                } else if (xDistance < -20) {
                    tetrisGame.moveLeft();
                } else if (yDistance > 20) {
                    tetrisGame.moveDown();
                }
                this.xEnd = (int) x;
                this.yEnd = (int) y;
            }
        }
    }

    public void touchUp() {
        if (Math.abs(xEnd - xStart) < 10 && Math.abs(yEnd - yStart) < 10) {
            if (xStart < 540) {  // DON'T HARDCODE
                tetrisGame.rotateCounterClockwise();
            } else {
                tetrisGame.rotateClockwise();
            }
        }
    }
}
