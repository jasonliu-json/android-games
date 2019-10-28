package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * A column of the rhythm, which consists of a shadow (the target)
 * and the notes to hit within the column.
 */
class Column {
    private int height = 100;
    private int colNumber;

    // the target, height from top
    private Target target;

    // the notes
    private ArrayList<Note> notes = new ArrayList<>();

    public Column(int height, int colNumber) {
//        this.height = height;
        this.colNumber = colNumber;
        this.target = new Target(5, 2);
    }

    public void update() {
        // move notes up
        for (Note note : notes) note.moveUp(1);
        // check to see if any notes are off screen and remove them
    }

    public void draw(Canvas canvas, int screenHeight, float colSize, NoteShape unitNoteShape, Paint notePaint, Paint targetPaint) {
        float heightScale = screenHeight / (float) this.height;
        // draw Shadow
        float xTarget = this.colNumber * colSize;
        NoteShape targetNoteShape = unitNoteShape.clone();
        targetNoteShape.setScale((colSize - 20) / unitNoteShape.getWidth());
        target.draw(canvas, xTarget, heightScale, targetNoteShape, targetPaint);

        // draw Notes
        float xNote = this.colNumber * colSize;
        NoteShape noteShape = unitNoteShape.clone();
        noteShape.setScale((colSize - 5) / unitNoteShape.getWidth());
        for (Note note : notes) note.draw(canvas, xNote, heightScale, noteShape, notePaint);
    }

    public void generateNote() {
        //notes.add(new Note());
    }

    public void tap() {
        // check to see if top note is in target
        notes.add(new Note(50));
        // if in target remove note and update points
//        if(target.contains(notes.get(0).getY())){
//            // add points
//            notes.remove(0);
//        }
    }
}
