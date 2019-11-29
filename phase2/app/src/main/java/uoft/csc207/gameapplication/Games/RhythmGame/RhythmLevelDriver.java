package uoft.csc207.gameapplication.Games.RhythmGame;

import android.graphics.Canvas;

import java.util.Observer;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.RhythmGamePresenter;

/**
 * The driver for a level of the Rhythm game.
 */
public class RhythmLevelDriver extends GameDriver {
    private RhythmGameLevel level;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;

    RhythmLevelDriver(RhythmGameLevel level, RhythmGameController controller,
                      RhythmGamePresenter presenter) {
        this.level = level;
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public void touchStart(float x, float y) {
        controller.touchStart(x,y);
    }

    @Override
    public void touchMove(float x, float y) {

    }

    @Override
    public void touchUp() {

    }

    @Override
    public void draw(Canvas canvas) {
        presenter.draw(canvas);
    }

    /**
     * Starts time and thread-related processes
     */
//    @Override
    public void start() {
        level.start();
        presenter.start();
    }

    /**
     * Stops time and thread-related processes
     */
//    @Override
    public void stop() {
        level.stop();
        presenter.stop();
    }

    /**
     * Updates the state of the level by one unit time.
     */
//    @Override
    public void timeUpdate() {
        level.timeUpdate();
    }

    public boolean getGameIsOver() {
        return level.getGameIsOver();
    }

    public int getPoints() {
        return level.getPoints();
    }

    void addObserver(Observer o) {
        level.addObserver(o);
    }
}
