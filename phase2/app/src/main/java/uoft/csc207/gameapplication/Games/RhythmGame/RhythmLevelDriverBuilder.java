package uoft.csc207.gameapplication.Games.RhythmGame;

import android.content.Context;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmLevelLivesMode;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmLevelSongMode;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.RGMissedPresenter;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.RGStatsPresenter;
import uoft.csc207.gameapplication.Games.RhythmGame.Presenter.RhythmGamePresenter;

/**
 * Builds the driver for one level of Rhythm game.
 * Builds the game logic, controller, and presenter.
 */
class RhythmLevelDriverBuilder {
    private RhythmLevelDriver driver;
    private RhythmGameLevel level;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;

    /**
     * Creates the Rhythm game based on the mode
     * @param mode "LIVES" the game continues until a number of notes missed,
     *             or "SONG" the game continues until the somg is over
     * @param numColumns number of note columns of the game
     * @param gameHeight the length of the columns
     * @param song the song played
     */
    void createRhythmLevel(String mode, int numColumns, int gameHeight, String song) {
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
    void createRhythmGameController(DisplayMetrics metrics) {
        controller = new RhythmGameController(level, metrics.widthPixels);
    }

    /**
     * Creates the presenter, which displays the game to the user
     * @param type "LIVES" shows the number of notess missed or "SONG" shows the player stats
     * @param metrics the dimensions of the screen
     * @param context the activity context
     * @param colourTheme the colour theme for the tetris pieces
     * @param shapes the shapes of the notes
     */
    void createRhythmGamePresenter(String type, DisplayMetrics metrics,
                                   Context context, String colourTheme, char[] shapes) {
        if (type.equalsIgnoreCase("LIVES"))
            presenter = new RGMissedPresenter(level, metrics, context, shapes);
        else if (type.equalsIgnoreCase("SONG"))
            presenter = new RGStatsPresenter(level, metrics, context, shapes);
        else
            presenter = new RhythmGamePresenter(level, metrics, context, shapes);
    }

    void createDriver() {
        driver = new RhythmLevelDriver(level, controller, presenter);
    }

    RhythmLevelDriver getDriver() {
        return driver;
    }
}
