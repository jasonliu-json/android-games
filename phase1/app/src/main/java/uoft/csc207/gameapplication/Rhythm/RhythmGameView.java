package uoft.csc207.gameapplication.Rhythm;

import android.content.Context;

import uoft.csc207.gameapplication.Game;
import uoft.csc207.gameapplication.GameView;

/* The view of the Rhythm game */
public class RhythmGameView extends GameView {
    /**
     * Create a new game in the context environment.
     *
     * @param context the environment.
     */
    public RhythmGameView(Context context) {
        super(context);
    }

    /**
     * Initializes the game with the given dimensions and returns it.
     *
     * @param screenHeight height of the screen
     * @param screenWidth  width of the screen
     * @return the Rhythm Game
     */
    @Override
    protected Game initializeGame(int screenHeight, int screenWidth) {
        RhythmGame rhythmGame = new RhythmGame(screenHeight, screenWidth);
        setOnTouchListener(rhythmGame.getTouchListener());
        return rhythmGame;
    }
}
