package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.Observer;

import uoft.csc207.gameapplication.GameDriver;
import uoft.csc207.gameapplication.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;

/**
 * The driver for a level of the Rhythm game.
 */
public class RhythmLevelDriver extends GameDriver {
    private Context context;

    private RhythmLevelDriver[] levelDrivers;
    private RhythmGameLevel level;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;

    private int numColumns = 4;

    public RhythmLevelDriver(RhythmGameLevel level, RhythmGameController controller,
                             RhythmGamePresenter presenter) {
        this.level = level;
        this.controller = controller;
        this.presenter = presenter;
    }

    public void init(DisplayMetrics metrics, String configs) {
        super.init(metrics);
//        String jsonString = "{}"
//        controller.setScreenWidth(getScreenWidth());
//        presenter.init(getScreenWidth(), getScreenHeight());
//        controller.setScreenWidth(screenWidth);
//        presenter.init(screenWidth, screenHeight);

        //TEMPORARY
        start();
    }

    @Override
    public void touchStart(float x, float y) {
        controller.touchStart(x,y);
    }

    @Override
    public void touchMove(float x, float y) {
        controller.touchMove(x,y);
    }

    @Override
    public void touchUp() {
        controller.touchUp();
    }

    @Override
    public void draw(Canvas canvas) {
        // TEMPORARY
        timeUpdate();
        presenter.draw(canvas);
    }

//    @Override

    /**
     * Starts time and thread-related processes
     */
    public void start() {
        level.start();
        presenter.start();
    }

//    @Override
    /**
     * Stops time and thread-related processes
     */
    public void stop() {
        level.stop();
        presenter.stop();
    }

    /**
     * Update the game state.
     */
    public void timeUpdate() {
        level.timeUpdate();
    }


    public boolean getGameIsOver() {
        return level.getGameIsOver();
    }

    public int getPoints() {
        return level.getPoints();
    }

    public void addObserver(Observer o) {
        level.addObserver(o);
    }

}
