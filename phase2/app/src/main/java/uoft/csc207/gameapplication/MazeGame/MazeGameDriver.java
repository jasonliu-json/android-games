package uoft.csc207.gameapplication.MazeGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import uoft.csc207.gameapplication.GameDriver;

import static java.lang.Thread.sleep;

public class MazeGameDriver extends GameDriver {

    private MazeGame mazeGame;
    /**
     * cursors initial x and y position when pressed down on the screen
     */
    private int xInit;
    private int yInit;

    /**
     * creates the MazeGameDriver that accesses the MazeGame
     *
     * @param context the activity where this driver is being used on
     */
    public MazeGameDriver(Context context) {
        mazeGame = new MazeGame();
    }

    /**
     * sets the init x and y position
     *
     * @param x coordinate of press
     * @param y coordinate of press
     */
    public void touchStart(float x, float y) {
        xInit = (int) x;
        yInit = (int) y;
    }

    /**
     * calls the MazeGame movement methods
     * @param x where the x position of the cursor when moved
     * @param y where the y position of the cursor when moved
     */
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

    /**
     * draws onto the canvas
     *
     * @param canvas the place where the picture will be drawn on
     */
    public void draw(Canvas canvas) {
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);
        newCanvas.scale(1.01f, 1.01f);

        mazeGame.draw(newCanvas, screenWidth, screenHeight);

        canvas.drawBitmap(bitmap, 0, 0, null);

        newCanvas.restore();
    }

    /**
     * @return whether the game is over or not
     */
    public boolean getGameIsOver() {
        return mazeGame.getGameIsOver();
    }

    /**
     *
     * @return the total points accumulated in the mazegame
     */
    public int getPoints() {
        return mazeGame.getPoints();
    }
}
