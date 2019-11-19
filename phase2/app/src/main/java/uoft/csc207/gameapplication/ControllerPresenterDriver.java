package uoft.csc207.gameapplication;

import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * May need to rename this class.
 * Is a GameDriver that delegates tasks to the game logic, controller, and presenter.
 */
public class ControllerPresenterDriver extends GameDriver{
    GameLogic gameLogic;
    GameController controller;
    GamePresenter presenter;

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            timeUpdate();
        }
    };

    Timer timer = new Timer();
    // Screen Dimensions
    private int screenWidth, screenHeight;

    public ControllerPresenterDriver(GameLogic gameLogic, GameController controller, GamePresenter presenter) {
        this.gameLogic = gameLogic;
        this.controller = controller;
        this.presenter = presenter;
    }

    public void init(DisplayMetrics metrics) {
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels - 40;

        //gameLogic.init(); // Is this needed?
        controller.init(screenWidth, screenHeight);
        presenter.init(screenWidth, screenHeight);
    }

    /**
     * When the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        controller.touchStart(x, y);
    }

    /**
     * As the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchMove(float x, float y) {
        controller.touchMove(x, y);
    }

    /**
     * When the touch is lifted off the screen.
     */
    public void touchUp() {
        controller.touchUp();
    }

    public void draw(Canvas canvas) {
        presenter.draw(canvas);
    }

    public void start() {
//        gameLogic.start();
//        presenter.start();
        timer.scheduleAtFixedRate(task, 0, 60);
    }

    public void stop() {
//        gameLogic.stop();
//        presenter.stop();
        timer.cancel();
        timer.purge();
    }

    public void timeUpdate() {
        gameLogic.timeUpdate();
    }

    public boolean getGameIsOver() {
        return gameLogic.getIsGameOver();
    }

    public int getPoints() {
        return gameLogic.getPoints();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
