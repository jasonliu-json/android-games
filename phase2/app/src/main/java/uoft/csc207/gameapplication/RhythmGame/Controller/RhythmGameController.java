package uoft.csc207.gameapplication.RhythmGame.Controller;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameLevel;

/**
 * How the player interacts with the RhythmGameLevel
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

    public void touchMove(float x, float y) {
    }

    public void touchUp() {
    }

//    public void setScreenWidth(int screenWidth) {
//        this.screenWidth = screenWidth;
//    }
}
