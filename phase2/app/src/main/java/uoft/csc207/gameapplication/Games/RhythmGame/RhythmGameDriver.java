package uoft.csc207.gameapplication.Games.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.Observable;
import java.util.Observer;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;

/**
 * The driver for the entire game. It selects the driver for an individual level and
 * selects the next driver when the level is over.
 */
public class RhythmGameDriver extends GameDriver implements Observer {
    private int totalPoints;
    private int levelIndex;
    private RhythmLevelDriver[] levelDrivers;

    public RhythmGameDriver(DisplayMetrics metrics, Context context, String configs) {
        this.totalPoints = 0;
        this.levelIndex = 0;
        this.levelDrivers = createLevelDrivers(metrics, context, configs);
    }

//    @Override
    public void timeUpdate() {
        levelDrivers[levelIndex].timeUpdate();
    }

//    @Override
    public void start() {
        levelDrivers[levelIndex].start();
    }

//    @Override
    public void stop() {
        levelDrivers[levelIndex].stop();
    }

    @Override
    public void touchStart(float x, float y) {
        levelDrivers[levelIndex].touchStart(x, y);
    }

    @Override
    public void touchMove(float x, float y) {

    }

    @Override
    public void touchUp() {

    }

    @Override
    public void draw(Canvas canvas) {
        levelDrivers[levelIndex].draw(canvas);
    }

    @Override
    public boolean getGameIsOver() {
        return levelIndex == levelDrivers.length - 1 && levelDrivers[levelIndex].getGameIsOver();
    }

    @Override
    public int getPoints() {
        return totalPoints + levelDrivers[levelIndex].getPoints();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (((String)o).equalsIgnoreCase(RhythmGameLevel.LEVEL_OVER_MESSAGE)) {
            stop();
            if (levelIndex < levelDrivers.length - 1) {
                levelIndex++;
                start();
            }
        }
    }

    private RhythmLevelDriver[] createLevelDrivers(DisplayMetrics metrics, Context context, String configs) {
        // First two elements describe configs for all levels
        String[] config = configs.split(";");
        String colourTheme = config[0];
        char[] shapes = config[1].toCharArray();
        RhythmLevelDriver[] levelDrivers = new  RhythmLevelDriver[config.length-2];

        // Builds a level driver, for each additional element of config array
        for (int i = 2; i < config.length; i++) {
            String[] levelConfig = config[i].split(",");
            String mode = levelConfig[0];
            int numColumns = Integer.parseInt(levelConfig[1]);
            int gameHeight = Integer.parseInt(levelConfig[2]);
            String song = levelConfig[3];

            RhythmLevelDriverBuilder builder = new RhythmLevelDriverBuilder();
            builder.createRhythmLevel(mode, numColumns, gameHeight, song);
            builder.createRhythmGameController(metrics);
            builder.createRhythmGamePresenter(mode, metrics, context, colourTheme, shapes);
            builder.createDriver();

            levelDrivers[i-2] = builder.getDriver();
            levelDrivers[i-2].addObserver(this);
        }




//        if (configs.charAt(0) == '3') {
////            levelDrivers = new RhythmLevelDriver[3];
//            // Settings for the three games
//            String[] songList = {"Mii Channel", "Old Town Road", "Third Song"};
//            int[] numColumnsList = {3, 4, 4};
//            int[] gameHeightList = {100, 100, 80};
//
//            for (int i = 0; i < 3; i++) {
//                builder.createRhythmLevel("SONG", numColumnsList[i],
//                        gameHeightList[i], songList[i]);
//                builder.createRhythmGameController(metrics);
//                builder.createRhythmGamePresenter("STATS", metrics, context, config[1]);
//                builder.createDriver();
//
//                levelDrivers[i] = builder.getDriver();
//                levelDrivers[i].addObserver(this);
//            }
//        } else {
//            levelDrivers = new RhythmLevelDriver[1];
//            builder.createRhythmLevel("LIVES", 4,
//                    250, "Old Town Road");
//            builder.createRhythmGameController(metrics);
//            builder.createRhythmGamePresenter("MISSED", metrics, context, configs.substring(1).toCharArray());
//            builder.createDriver();
//
//            levelDrivers[0] = builder.getDriver();
//            levelDrivers[0].addObserver(this);
//        }

        return levelDrivers;
    }


}
