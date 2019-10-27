package uoft.csc207.gameapplication.Rhythm;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * A column of the rhythm, which consists of a shadow (the target)
 * and the notes to hit within the column.
 */
class Column {
    // the x position of the most left edge of the column
    private int x;

    // the shape and colour of the notes
    private NoteShape noteShape;
    private Paint paint;

    // the target
    private NoteShadow shadow;
    // the notes
    private ArrayList<Note> notes;

    public Column(int x, NoteShape noteShape, Paint paint) {
        this.x = x;
        this.noteShape = noteShape;
        this.paint = paint;
        this.shadow = new NoteShadow(x +15, 15, noteShape);
    }

    public void update() {
        // move notes up
        // check to see if any notes are off screen and remove them
    }

    public void draw(Canvas canvas) {
        // draw Shadow
        // draw Notes
        //for (Note note : notes) note.draw(canvas);
    }

    public void generateNote() {
        //notes.add(new Note());
    }

    public void tap() {
        // check to see if top note is in target
        // if in target remove note and update points
    }
}
