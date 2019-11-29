package uoft.csc207.gameapplication.Games.GameWrapper;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.GameDriver;

/**
 * Handles the inputs and outputs of the game.
 */
public class GameWrapperDriver extends GameDriver {
    private GameWrapper gameWrapper;

    public GameWrapperDriver() {
        gameWrapper = new GameWrapper();
    }

    /**
     * Called when the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        gameWrapper.touchStart(x, y);
    }

    /**
     * Called as the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchMove(float x, float y) {
        gameWrapper.touchMove(x, y);
    }

    /**
     * Called when the touch is lifted off the screen.
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
//    public void setGameState(int setPoints, int gameState) {
//        gameWrapper.setPoints(setPoints);
//        gameWrapper.setGameState(gameState);
//    }
//
//    public int getGameState() {
//        return gameWrapper.getGamesPlayed();
//    }

    public void start() {
        gameWrapper.start();
    }

    public void stop() {
        gameWrapper.stop();
    }

    public void timeUpdate() {
        gameWrapper.timeUpdate();
    }

    @Override
    public void setMetrics(DisplayMetrics metrics) {
        gameWrapper.setMetrics(metrics);
    }

    @Override
    public void setContext(Context context) {
        gameWrapper.setContext(context);
    }

    @Override
    public void setConfigurations(String configurations) {
        gameWrapper.setConfiguration(configurations);
    }
}
