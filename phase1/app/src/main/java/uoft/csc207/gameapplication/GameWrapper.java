package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameDriver;
import uoft.csc207.gameapplication.TetrisGame.TetrisGameDriver;

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
        int currentGamePoints = gameDriver.getPoints();
        if (gameDriver.getGameIsOver()) {
            gamesPlayed += 1;
            points += currentGamePoints;
            if (gamesPlayed == 1) {
                gameDriver = new RhythmGameDriver(context);
                gameDriver.init(metrics);
            }
            else if (gamesPlayed == 2) {
                gameDriver = new MazeGameDriver(context);
                gameDriver.init(metrics);
            }
            else {
                gameIsOver = true;
            }
        }
        gameDriver.draw(canvas);
        canvas.drawText(String.valueOf(points + currentGamePoints), 10, 80, textPaint);
    }

    boolean getGameIsOver() {
        return gameIsOver;
    }

    int getPoints() {
        return points;
    }
}
