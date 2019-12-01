package uoft.csc207.gameapplication.Games.RhythmGame;

import android.graphics.Canvas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.RhythmGamePresenter;

/**
 * The driver for the entire game. It selects the driver for an individual level and
 * selects the next driver when the level is over.
 */
public class RhythmGameDriver extends GameDriver implements Observer {
    private int totalPoints;
    private int levelIndex;

    private RhythmGameLevel[] levels;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;


    public RhythmGameDriver() {
        this.totalPoints = 0;
        this.levelIndex = 0;
    }

    @Override
    public void timeUpdate() {
        levels[levelIndex].timeUpdate();
    }

    @Override
    public void start() {
        levels[levelIndex].start();
        presenter.start();
    }

    @Override
    public void init() {
        try {
            createLevels(getConfigurations().getJSONArray("levels"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.controller = new RhythmGameController(levels[levelIndex], getMetrics().widthPixels);

        char[] shapes = new char[4];
        for (int i = 1; i <= 4; i++) {
            try {
                shapes[i-1] = getConfigurations().getString(String.format(Locale.CANADA, 
                        "Shape%d", i)).charAt(0);
            } catch (JSONException e) {
                shapes[i - 1] = 'O';
            }
        }

        String statDrawerMode;
        try {
            statDrawerMode = getConfigurations().getString("presenterMode");
        } catch (JSONException e) {
            statDrawerMode = "STATS";
        }

        this.presenter = new RhythmGamePresenter(levels[levelIndex], getMetrics(), getContext(), shapes,
                getColourScheme(), statDrawerMode);
    }

    private void createLevels(JSONArray levelsArray) {
        this.levels = new RhythmGameLevel[levelsArray.length()];
        // Creates each level based on the config
        for (int i = 0; i < levelsArray.length(); i++) {
            try {
                JSONObject levelJSON = levelsArray.getJSONObject(i);
                int numColumns = levelJSON.getInt("numColumns");
                int gameHeight = levelJSON.getInt("height");
                String song = levelJSON.getString("song");
                String mode = levelJSON.getString("mode");
                levels[i] = new RhythmGameLevel(numColumns, gameHeight, song, mode);
            } catch (JSONException e) {
                levels[i] = new RhythmGameLevel(4, 100,
                        "Old Town Road", "SONG");
            }

            levels[i].addObserver(this);
        }
    }

    @Override
    public void stop() {
        levels[levelIndex].stop();
        presenter.stop();
    }

    @Override
    public void touchStart(float x, float y) {
        controller.touchStart(x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        presenter.draw(canvas);
    }

    @Override
    public boolean getGameIsOver() {
        return levelIndex == levels.length - 1 && levels[levelIndex].getGameIsOver();
    }

    @Override
    public int getPoints() {
        return totalPoints + levels[levelIndex].getPoints();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (((String)o).equalsIgnoreCase(RhythmGameLevel.LEVEL_OVER_MESSAGE)) {
            if (levelIndex < levels.length - 1) {
                stop();
                this.totalPoints += levels[levelIndex].getPoints();
                levelIndex++;
                controller.setLevel(levels[levelIndex]);
                presenter.setLevel(levels[levelIndex]);
                start();
            }
        }
    }
}
