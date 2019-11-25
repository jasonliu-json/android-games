package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.Observer;

import uoft.csc207.gameapplication.GameDriver;
import uoft.csc207.gameapplication.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;
import uoft.csc207.gameapplication.RhythmGame.NoteGenerator.NoteGenerator;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;

/**
 * The driver for Rhythm Game.
 */
public class RhythmGameDriver extends GameDriver {
    private RhythmGame rhythmGame;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;
    private NoteGenerator noteGenerator;

    private int numColumns = 4;
    private String[] colMessages = new String[numColumns];

    public RhythmGameDriver(RhythmGame rhythmGame, RhythmGameController controller,
                            RhythmGamePresenter presenter, NoteGenerator noteGenerator) {
        this.rhythmGame = rhythmGame;
        this.controller = controller;
        this.presenter = presenter;
        this.noteGenerator = noteGenerator;
    }

    @Override
    public void init(DisplayMetrics metrics) {
        super.init(metrics);
//        controller.setScreenWidth(getScreenWidth());
//        presenter.init(getScreenWidth(), getScreenHeight());
        controller.setScreenWidth(screenWidth);
        presenter.init(screenWidth, screenHeight);
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
        presenter.draw(canvas);
    }

//    @Override

    /**
     * Starts time and thread-related processes
     */
    public void start() {
        presenter.start();
        noteGenerator.start();
    }

//    @Override
    /**
     * Stops time and thread-related processes
     */
    public void stop() {
        presenter.stop();
        noteGenerator.stop();
    }

    /**
     * Update the game state.
     */
    public void timeUpdate() {
        rhythmGame.timeUpdate();
        noteGenerator.timeUpdate();
    }

    public void addObserver(Observer obs) {
        rhythmGame.addObserver(obs);
    }

    public boolean getGameIsOver() {
        return rhythmGame.getIsGameOver();
    }

    public int getPoints() {
        return rhythmGame.getPoints();
    }

}
