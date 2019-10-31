package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameDriver;
import uoft.csc207.gameapplication.TetrisGame.TetrisGameDriver;

public class GameWrapper {
    private GameDriver gameDriver;

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
    }

    void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
        gameDriver = new TetrisGameDriver(context);
        gameDriver.init(this.metrics);
    }
    void touchStart(float x, float y) {
        gameDriver.touchStart(x, y);
    }

    void touchMove(float x, float y) {
        gameDriver.touchMove(x, y);
    }

    void touchUp() {
        gameDriver.touchUp();
    }

    void draw(Canvas canvas) {
        if (gameDriver.getGameIsOver()) {
            gamesPlayed += 1;
            if (gamesPlayed == 1) {
                gameDriver = new RhythmGameDriver(context);
                gameDriver.init(metrics);
            }
            else if (gamesPlayed == 2) {
                gameDriver = new MazeGameDriver(context);
                gameDriver.init(metrics);
            }
            else {
                // use intent to send back to login should be main menu in future
            }
        }
        gameDriver.draw(canvas);
    }

    boolean getGameIsOver() {
        return gameIsOver;
    }

    int getPoints() {
        return points;
    }
}
