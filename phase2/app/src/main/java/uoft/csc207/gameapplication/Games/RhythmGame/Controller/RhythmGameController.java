package uoft.csc207.gameapplication.Games.RhythmGame.Controller;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;

/**
 * Controller that interacts with a Rhythm level
 */
public class RhythmGameController {
    private RhythmGameLevel level;
    private int numColumns;

    private int screenWidth;

    public RhythmGameController(RhythmGameLevel level, int screenWidth) {
        this.level = level;
        this.numColumns = level.getNumColumns();
        this.screenWidth = screenWidth;
    }

    /**
     * Taps the game.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        // Determines the column number based on the screen width
        int colNumber = (int) (numColumns * x / screenWidth);
        level.tap(colNumber);
    }
}
