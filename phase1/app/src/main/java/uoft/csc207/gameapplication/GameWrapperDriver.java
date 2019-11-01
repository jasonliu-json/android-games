package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGame;

import static java.lang.Thread.sleep;

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
    public void touchStart(float x, float y) {
        gameWrapper.touchStart(x, y);
    }

    public void touchMove(float x, float y) {
        gameWrapper.touchMove(x, y);
    }

    public void touchUp() {
        gameWrapper.touchUp();
    }

    public void draw(Canvas canvas) {
        gameWrapper.draw(canvas);
    }

    public boolean getGameIsOver() {
        return gameWrapper.getGameIsOver();
    }

    public int getPoints() {
        return gameWrapper.getPoints();
    }

    public int getGameState() {
        return gameWrapper.getGamesPlayed();
    }
    public void setGameState(int setPoints, int gameState) {
        gameWrapper.setPoints(setPoints);
        gameWrapper.setGameState(gameState);
    }
}
