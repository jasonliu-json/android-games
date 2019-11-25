package uoft.csc207.gameapplication.RhythmGame.Controller;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;

/**
 * How the player interacts with the RhythmGame
 */
public class RhythmGameController {
    private RhythmGame rhythmGame;
    private int numColumns;

    private int screenWidth;

    public RhythmGameController(RhythmGame rhythmGame) {
        this.rhythmGame = rhythmGame;
        this.numColumns = rhythmGame.getNumColumns();
    }

    /**
     * Taps the game.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        // Determines the column number based on the screen width
        int colNumber = (int) (numColumns * x / screenWidth);
        rhythmGame.tap(colNumber);
    }

    public void touchMove(float x, float y) {
    }

    public void touchUp() {
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }
}
