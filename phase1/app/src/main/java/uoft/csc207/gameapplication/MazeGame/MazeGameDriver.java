package uoft.csc207.gameapplication.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;

import uoft.csc207.gameapplication.GameDriver;

import static java.lang.Thread.sleep;

public class MazeGameDriver extends GameDriver {

    private MazeGame mazeGame;

    private int xInit;
    private int yInit;

    public MazeGameDriver() {
        mazeGame = new MazeGame();
    }

    public void touchStart(float x, float y) {
        xInit = (int) x;
        yInit = (int) y;
    }

    public void touchMove(float x, float y) {
        try {
            sleep(100);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            int xDistance = (int) x - xInit;
            int yDistance = (int) y - yInit;
            if (Math.abs(xDistance) > Math.abs(yDistance)) {
                if (xDistance > 0) {
                    mazeGame.moveRight();
                }
                else {
                    mazeGame.moveLeft();
                }
            }
            else {
                if (yDistance > 0) {
                    mazeGame.moveDown();
                }
                else {
                    mazeGame.moveUp();
                }
            }
        }
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);
        newCanvas.scale(1.01f, 1.01f);

        mazeGame.draw(newCanvas, screenWidth, screenHeight);

        canvas.drawBitmap(bitmap, 0, 0, null);

        newCanvas.restore();
    }
}
