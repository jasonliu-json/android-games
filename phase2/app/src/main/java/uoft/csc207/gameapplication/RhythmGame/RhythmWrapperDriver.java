package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.Observable;
import java.util.Observer;

import uoft.csc207.gameapplication.GameDriver;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;

public class RhythmWrapperDriver extends GameDriver implements Observer {

    private int totalPoints;
    private int gameIndex;
    private final int NUM_LEVELS = 3;
    private RhythmGameDriver[] levelDrivers = new RhythmGameDriver[NUM_LEVELS];

    public RhythmWrapperDriver(Context context) {
        totalPoints = 0;

        // Settings for the three games
        RhythmGamePresenter.Song[] songList = {RhythmGamePresenter.Song.MII_CHANNEL,
                RhythmGamePresenter.Song.OLD_TOWN_ROAD, RhythmGamePresenter.Song.OLD_TOWN_ROAD};
        int[] numColumnsList = {3, 4, 4};
        int[] gameHeightList = {100, 100, 80};

        RhythmGameDriverBuilder builder = new RhythmGameDriverBuilder();;
        for (int i = 0; i < NUM_LEVELS; i++) {
            builder.createRhythmGame("SONG", numColumnsList[i],
                    gameHeightList[i], context);
            builder.createRhythmGameController();
            builder.createRhythmGamePresenter(songList[i]);
            builder.createNoteGenerator();
            builder.createDriver();

            levelDrivers[i] = builder.getDriver();
            levelDrivers[i].addObserver(this);
        }
    }
    public void update() {

    }

    @Override
    public void init(DisplayMetrics metrics) {

    }
    @Override
    public void touchStart(float x, float y) {
        levelDrivers[gameIndex].touchStart(x, y);
    }

    @Override
    public void touchMove(float x, float y) {
        levelDrivers[gameIndex].touchMove(x, y);
    }

    @Override
    public void touchUp() {
        levelDrivers[gameIndex].touchUp();
    }

    @Override
    public void draw(Canvas canvas) {
        levelDrivers[gameIndex].draw(canvas);
    }

    @Override
    public boolean getGameIsOver() {
        return gameIndex == NUM_LEVELS - 1 && levelDrivers[gameIndex].getGameIsOver();
    }

    @Override
    public int getPoints() {
        return totalPoints;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (((RhythmGame)observable).getIsGameOver()) {
            if (gameIndex < NUM_LEVELS -1)
                gameIndex++;
        }
    }

}
