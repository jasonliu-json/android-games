package uoft.csc207.gameapplication.RhythmGame;

/**
 * A note in the game.
 */
public class Note {
    // y indicates the height (position) of the note
    private int y;

    Note(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    void moveUp(int unit) {
        y -= unit;
    }
}
