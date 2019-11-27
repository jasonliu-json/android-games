package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;

import uoft.csc207.gameapplication.RhythmGame.Controller.RhythmGameController;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;
import uoft.csc207.gameapplication.RhythmGame.NoteGenerator.NoteGenerator;
import uoft.csc207.gameapplication.RhythmGame.NoteGenerator.RandomNoteGenerator;
import uoft.csc207.gameapplication.RhythmGame.NoteGenerator.SongNoteGenerator;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RGMissedPresenter;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RGStatsPresenter;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;

public class RhythmGameDriverBuilder {
    private RhythmGameDriver driver;
    private RhythmGame rhythmGame;
    private RhythmGameController controller;
    private RhythmGamePresenter presenter;
    private NoteGenerator noteGenerator;
    private String mode = "LIVES";
    private RhythmGamePresenter.Song song = RhythmGamePresenter.Song.OLD_TOWN_ROAD;

    /**
     * Creates the Rhythm Game based on the mode
     * @param mode "LIVES" the game continues until a number of notes missed,
     *             or "SONG" the game continues until the somg is over
     * @param numColumns number of note columns the game has
     * @param gameHeight the length of the columns
     * @param context the context
     */
    public void createRhythmGame(String mode, int numColumns, int gameHeight, Context context) {
        this.mode = mode;
        if (mode.equalsIgnoreCase("LIVES"))
            rhythmGame = new RhythmGame(context, numColumns, gameHeight);
        else if (mode.equalsIgnoreCase("SONG"))
            rhythmGame = new RhythmGame(context, numColumns, gameHeight);
        else
            rhythmGame = new RhythmGame(context, numColumns, gameHeight);
    }

    /**
     * Creates the controller, which gets player interactions
     */
    public void createRhythmGameController() {
        controller = new RhythmGameController(rhythmGame);
    }

    /**
     * Creates the presenter of the Rhythm Game
     * @param song
     */
    public void createRhythmGamePresenter(RhythmGamePresenter.Song song) {
        this.song = song;
        if (mode.equalsIgnoreCase("LIVES")) {
            presenter = new RGMissedPresenter(rhythmGame, song);

            System.out.println("new RGMissedPresenter");
        } else if (mode.equalsIgnoreCase("SONG")) {
            presenter = new RGStatsPresenter(rhythmGame, song);

            System.out.println("new RGStatsPresenter");
        } else
            presenter = new RhythmGamePresenter(rhythmGame, RhythmGamePresenter.Song.OLD_TOWN_ROAD);
    }

    /**
     * Creates the note generator based on the mode
     */
    public void createNoteGenerator() {
        if (mode.equalsIgnoreCase("LIVES")) {
            noteGenerator = new RandomNoteGenerator(rhythmGame);
            System.out.println("new RNG");
        } else if (mode.equalsIgnoreCase("SONG")) {
            noteGenerator = new SongNoteGenerator(rhythmGame);
            System.out.println("new Song");
        } else
            noteGenerator = new NoteGenerator(rhythmGame);
    }

    public void createDriver() {
        driver = new RhythmGameDriver(rhythmGame, controller, presenter, noteGenerator);
    }

    public RhythmGameDriver getDriver() {
        return driver;
    }
}
