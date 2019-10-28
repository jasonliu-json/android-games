package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A note in the game.
 */
public class Note {
    // y indicates the height of the note
    private int y;

    public Note(int y) {
        this.y = y;
    }

    public void draw(Canvas canvas, float x, float heightScale, NoteShape shape, Paint paint) {
        shape.draw(canvas, x, y * heightScale, paint);
    }

    public int getY() {
        return y;
    }

    public void moveUp(float unit) {
        y -= unit;
    }
}
