package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGame;

import static java.lang.Thread.sleep;

/**
 * Handles the inputs and outputs of the game.
 */
public class GameWrapperDriver extends GameDriver {
    private GameWrapper gameWrapper;

    private int xInit;
    private int yInit;

    public GameWrapperDriver(Context context) {
        gameWrapper = new GameWrapper(context);
    }
    @Override
    public void init(DisplayMetrics metrics) {
        gameWrapper.setMetrics(metrics);
    }
    /**
     * When the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        gameWrapper.touchStart(x, y);
    }

    /**
     * As the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchMove(float x, float y) {
        gameWrapper.touchMove(x, y);
    }

    /**
     * When the touch is lifted off the screen.
     */
    public void touchUp() {
        gameWrapper.touchUp();
    }

    /**
     * Draws the game to canvas.
     * @param canvas the canvas to draw on.
     */
    public void draw(Canvas canvas) {
        gameWrapper.draw(canvas);
    }

    public boolean getGameIsOver() {
        return gameWrapper.getGameIsOver();
    }

    public int getPoints() {
        return gameWrapper.getPoints();
    }

    // Sets the state of the game with the previous points
    public void setGameState(int setPoints, int gameState) {
        gameWrapper.setPoints(setPoints);
        gameWrapper.setGameState(gameState);
    }

    public int getGameState() {
        return gameWrapper.getGamesPlayed();
    }
}
