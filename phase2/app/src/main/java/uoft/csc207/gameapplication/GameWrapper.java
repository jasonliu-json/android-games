package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameDriverBuilder;
import uoft.csc207.gameapplication.TetrisGame.TetrisGameDriver;

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


    public GameWrapper(Context context) {
        points = 0;
        gameIsOver = false;
        gamesPlayed = 0;
        this.context = context;
        textPaint.setColor(Color.rgb(255, 141, 54));
        textPaint.setTextSize(60);
    }

    void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
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
        if (gameDriver.getGameIsOver()) {
            gamesPlayed += 1;
            points += currentGamePoints;
            if (gamesPlayed == 1) {
                setUpRhythmGameDriver();
            }
            else if (gamesPlayed == 2) {
                gameDriver = new MazeGameDriver(context);
                gameDriver.init(metrics);
            }
            else {
                gameIsOver = true;
            }
        }
        else {
            gameDriver.draw(canvas);
            canvas.drawText(String.valueOf(points + currentGamePoints), 10, 80, textPaint);
        }
    }

    /**
     * Sets the state of the game based on player's progress
     * @param gameState
     */
    void setGameState(int gameState) {
        gamesPlayed = gameState;
        if (gameState == 0) {
            gameDriver = new TetrisGameDriver(context);
            gameDriver.init(this.metrics);
        }
        if (gameState == 1) {
            setUpRhythmGameDriver();
        }
        else if (gameState == 2) {
            gameDriver = new MazeGameDriver(context);
            gameDriver.init(metrics);
        }
    }

    private void setUpRhythmGameDriver() {
        RhythmGameDriverBuilder builder = new RhythmGameDriverBuilder();;
        builder.createRhythmGame("LIVES", 4, 100, context);
        builder.createRhythmGameController();
        builder.createRhythmGamePresenter(RhythmGamePresenter.Song.OLD_TOWN_ROAD);
        builder.createNoteGenerator();
        builder.createDriver();

        gameDriver = builder.getDriver();
        gameDriver.init(metrics);
    }

    boolean getGameIsOver() {
        return gameIsOver;
    }

    int getPoints() {
        return points;
    }

    int getGamesPlayed() {
        return gamesPlayed;
    }

    void setPoints(int setPoints) {
        points = setPoints;
    }
}
