package uoft.csc207.gameapplication.Games.GameWrapper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import org.json.JSONException;
import org.json.JSONObject;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.RhythmGameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameDriver;

/**
 * Plays the subgames in consecutive order.
 */
public class GameWrapperDriver extends GameDriver{
    private GameDriver gameDriver;

    private Paint textPaint = new Paint();
    private boolean gameIsOver;
    private int points;
    private int gamesPlayed;

    public GameWrapperDriver() {
        points = 0;
        gameIsOver = false;
        gamesPlayed = 0;
        textPaint.setColor(Color.rgb(255, 141, 54));
        textPaint.setTextSize(60);
    }

    /**
     * Called when the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        gameDriver.touchStart(x, y);
    }

    /**
     * Called as the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchMove(float x, float y) {
        gameDriver.touchMove(x, y);
    }

    /**
     * Called when the touch is lifted off the screen.
     */
    public void touchUp() {
        gameDriver.touchUp();
    }

    /**
     * Draws the current game selected
     * @param canvas the canvas to draw the game on
     */
    public void draw(Canvas canvas) {
        int currentGamePoints = gameDriver.getPoints();
        gameDriver.draw(canvas);
        canvas.drawText(String.valueOf(points + currentGamePoints), 10, 80, textPaint);
    }

    public void init() {
        gameDriver = new TetrisGameDriver();
        try {
            gameDriver.setConfigurations(getConfigurations().getJSONObject("tetris"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gameDriver.setMetrics(getMetrics());
        gameDriver.setContext(getContext());
        gameDriver.setColourScheme(getColourScheme());
        gameDriver.init();
    }

    public void start() {
        gameDriver.start();
    }

    public void timeUpdate() {
        gameDriver.timeUpdate();

        // Select next driver when game is over
        if (gameDriver.getGameIsOver()) {
            gamesPlayed++;
            points += gameDriver.getPoints();
            try {
                if (gamesPlayed == 1) {
                    gameDriver = new RhythmGameDriver();
                    gameDriver.setConfigurations(getConfigurations().getJSONObject("rhythm"));
                } else if (gamesPlayed == 2) {
                    gameDriver = new MazeGameDriver(getContext());
                    gameDriver.setConfigurations(getConfigurations().getJSONObject("maze"));
                } else {
                    gameIsOver = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            gameDriver.setMetrics(getMetrics());
            gameDriver.setContext(getContext());
            gameDriver.setColourScheme(getColourScheme());
            gameDriver.init();
            gameDriver.start();
        }
    }

    public void stop() {
        gameDriver.stop();
    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }

    public int getPoints() {
        return points;
    }
}
