package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmLevelLivesMode;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmLevelSongMode;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RGMissedPresenter;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RGStatsPresenter;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;

/**
 * Builds the driver for one level of Rhythm game. Builds the game logic, controller, and presenter.
 */
public class RhythmLevelDriverBuilder {
    private RhythmLevelDriver driver;
    private RhythmGameLevel level;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;

    /**
     * Creates the Rhythm Game based on the mode
     * @param mode "LIVES" the game continues until a number of notes missed,
     *             or "SONG" the game continues until the somg is over
     * @param numColumns number of note columns the game has
     * @param gameHeight the length of the columns
     * @param context the context
     */
    public void createRhythmLevel(String mode, int numColumns, int gameHeight, String song) {
        if (mode.equalsIgnoreCase("LIVES"))
            level = new RhythmLevelLivesMode(numColumns, gameHeight, song);
        else if (mode.equalsIgnoreCase("SONG"))
            level = new RhythmLevelSongMode(numColumns, gameHeight, song);
        else
            level = new RhythmLevelLivesMode(numColumns, gameHeight, song);
    }

    /**
     * Creates the controller, which gets player interactions
     */
    public void createRhythmGameController(DisplayMetrics metrics) {
        controller = new RhythmGameController(level, metrics.widthPixels);
    }

    /**
     * Creates the presenter of the Rhythm Game
     * @param song
     */
    public void createRhythmGamePresenter(String type, DisplayMetrics metrics, Context context, char[] shapes) {

        if (type.equalsIgnoreCase("MISSED"))
            presenter = new RGMissedPresenter(level, metrics, context, shapes);
        else if (type.equalsIgnoreCase("STATS"))
            presenter = new RGStatsPresenter(level, metrics, context, shapes);
        else
            presenter = new RhythmGamePresenter(level, metrics, context, shapes);
    }

    public void createDriver() {
        driver = new RhythmLevelDriver(level, controller, presenter);
    }

    public RhythmLevelDriver getDriver() {
        return driver;
    }
}
