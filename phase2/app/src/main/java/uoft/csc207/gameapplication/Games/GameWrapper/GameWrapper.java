package uoft.csc207.gameapplication.Games.GameWrapper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.RhythmGameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameDriver;

/**
 * Plays the subgames in consecutive order.
 */
public class GameWrapper {
    private GameDriver gameDriver;

    private Paint textPaint = new Paint();
    private boolean gameIsOver;
    private int points;
    private int gamesPlayed;
    private Context context;
    private DisplayMetrics metrics;
    private String[] configurations;

    public GameWrapper() {
        points = 0;
        gameIsOver = false;
        gamesPlayed = 0;
//        this.context = context;
        textPaint.setColor(Color.rgb(255, 141, 54));
        textPaint.setTextSize(60);
//        this.metrics = metrics;
    }

    /**
     * When the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    void touchStart(float x, float y) {
        gameDriver.touchStart(x, y);
    }

    /**
     * As the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    void touchMove(float x, float y) {
        gameDriver.touchMove(x, y);
    }

    /**
     * When the touch is lifted off the screen.
     */
    void touchUp() {
        gameDriver.touchUp();
    }

    /**
     * Draws the corresponding game based on the user's progress.
     * @param canvas the canvas to draw the game on
     */
    void draw(Canvas canvas) {
        int currentGamePoints = gameDriver.getPoints();
        gameDriver.draw(canvas);
        canvas.drawText(String.valueOf(points + currentGamePoints), 10, 80, textPaint);
    }

    public void start() {
        setGameState(0);
        gameDriver.start();
    }

    public void timeUpdate() {
        gameDriver.timeUpdate();

        if (gameDriver.getGameIsOver()) {
            gamesPlayed += 1;
            points += gameDriver.getPoints();
            if (gamesPlayed == 1) {
                gameDriver = new RhythmGameDriver();
                gameDriver.setMetrics(metrics);
                gameDriver.setContext(context);
                gameDriver.setConfigurations(configurations[1]);
                gameDriver.init();
                gameDriver.start();
            }
            else if (gamesPlayed == 2) {
                gameDriver = new MazeGameDriver(context);
                gameDriver.init(metrics);
            }
            else {
                gameIsOver = true;
            }

        }
    }

    public void stop() {
        gameDriver.stop();
    }

    /**
     * Sets the state of the game based on player's progress
     * @param gameState
     */
    void setGameState(int gameState) {
        gamesPlayed = gameState;
        if (gameState == 0) {
            gameDriver = new TetrisGameDriver();
            gameDriver.init(this.metrics);
        }
        if (gameState == 1) {
            gameDriver = new RhythmGameDriver();
            gameDriver.setMetrics(metrics);
            gameDriver.setContext(context);
            gameDriver.setConfigurations(configurations[1]); //This config string for testing);
            gameDriver.start();
        }
        else if (gameState == 2) {
            gameDriver = new MazeGameDriver(context);
            gameDriver.init(metrics);
        }
    }

    boolean getGameIsOver() {
        return gameIsOver;
    }

    int getPoints() {
        return points;
    }

//    int getGamesPlayed() {
//        return gamesPlayed;
//    }

    void setPoints(int setPoints) {
        points = setPoints;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    void setConfiguration(String configurations) {
        this.configurations = configurations.split(":");
    }
}
