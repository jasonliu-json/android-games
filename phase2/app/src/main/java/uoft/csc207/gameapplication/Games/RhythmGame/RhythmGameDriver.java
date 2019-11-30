package uoft.csc207.gameapplication.Games.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
//import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmLevelLivesMode;
//import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmLevelSongMode;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.MainStatsDrawer;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.MissedStatsDrawer;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.RhythmGamePresenter;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.StatsDrawer;

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

    private DisplayMetrics metrics;
    private Context context;
    private String configurations;
    private Map<String, Integer> colourScheme;

    public RhythmGameDriver() {
        this.totalPoints = 0;
        this.levelIndex = 0;
    }

    @Override
    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setConfigurations(String configurations) {
        this.configurations = configurations;
    }

    @Override
    public void setColourScheme(Map<String, Integer> colourScheme) {
        this.colourScheme = colourScheme;
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
        String[] configs = configurations.split(";");
        String[] levelsConfig = Arrays.copyOfRange(configs, 3,configs.length);
        createLevels(levelsConfig);

        this.controller = new RhythmGameController(levels[levelIndex], metrics.widthPixels);

        // First three elements describe configs for all levels of presenter
//        String colourTheme = configs[0];
        char[] shapes = configs[1].toCharArray();
        String statDrawerMode = configs[2];
        this.presenter = new RhythmGamePresenter(levels[levelIndex], metrics, context, shapes,
                colourScheme, statDrawerMode);
    }

    private void createLevels(String[] configs) {
        this.levels = new RhythmGameLevel[configs.length];
        // Creates each level based on the config
        for (int i = 0; i < configs.length; i++) {
            String[] levelConfig = configs[i].split(",");
            int numColumns = Integer.parseInt(levelConfig[0]);
            int gameHeight = Integer.parseInt(levelConfig[1]);
            String song = levelConfig[2];
            String mode = levelConfig[3];

            levels[i] = new RhythmGameLevel(numColumns, gameHeight, song, mode);

//            if (mode.equalsIgnoreCase("LIVES"))
//                levels[i] = new RhythmLevelLivesMode(numColumns, gameHeight, song);
//            else
//                levels[i] = new RhythmLevelSongMode(numColumns, gameHeight, song);
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
