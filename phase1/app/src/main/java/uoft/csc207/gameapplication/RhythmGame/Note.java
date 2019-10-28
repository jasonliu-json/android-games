package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A note in the game.
 */
public class Note {
    // x, y indicates the top left corner of the note
    private int x, y;
    private int scale;
    private NoteShape noteShape;
    private Paint paint;

    public Note(int x, int y, NoteShape noteShape, Paint paint) {
        this.x = x;
        this.y = y;
        this.noteShape = noteShape;
        this.paint = paint;
    }

    public void draw(Canvas canvas) {
        noteShape.draw(x, y, canvas, paint);
    }

    public RectF getDirtyBounds() {
        return new RectF(x, y, x + noteShape.getWidth(), y + noteShape.getHeight());
    }

    public void moveUp(float unit) {
        y -= unit;
    }
}
