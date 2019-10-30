package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import java.util.ArrayList;

import uoft.csc207.gameapplication.GameActivity;

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
    private ArrayList<Note> notes;

    public Column(int height, int colNumber) {
//        this.height = height;
        this.colNumber = colNumber;
        this.target = new Target(10, 10);
        notes = new ArrayList<>();
    }

  public void update() {
    // move notes up

    ArrayList<Note> notesCopy = new ArrayList<>(notes);
    for (Note note : notesCopy) {
      note.moveUp(1);

      // note: getY() > ... should be screen height?
      if (note.getY() > 2 * height || note.getY() < -18) {
        notes.remove(note);
        RhythmGame.changeScore(-1);

      }
    }
    // check to see if any notes are off screen and remove them
  }

  public boolean checkLowestNote() {
        int lowest = 0;
        for (Note note: notes) {
            if (note.getY() > lowest) {
                lowest = note.getY();
            }
        }
        // screen height = 100, note height = 20??
        return 100 - lowest > 20;
  }

  public void draw(
      Canvas canvas,
      int screenHeight,
      float colSize,
      NoteShape unitNoteShape,
      Paint notePaint,
      Paint targetPaint) {
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
      if (checkLowestNote()) {
          notes.add(new Note(height));
      }
  }

  public void tap() {
    // check to see if top note is in target

    // adds new note
    //        notes.add(new Note(50));

    // if in target remove note and update points

    ArrayList<Note> notesCopy = new ArrayList<>(notes);



        for (int i=0; i<notesCopy.size(); i++){
            if(target.contains(notes.get(0).getY())){
                // add points

                // score gained is based on the difference between hit position and target, for
                // maximum of 10 points per hit.
                double howClose = (notes.get(0).getY() - target.getY()) / target.getAllowedError();
                int scoreGained = 5 * (int) howClose;

                if (howClose < 0.1) {
                    RhythmGame.displayMessage("Perfect!");
                } else if (howClose < 0.4) {
                    RhythmGame.displayMessage("Great!");
                } else {
                    RhythmGame.displayMessage("Good!");
                }
                notes.remove(0);
                RhythmGame.changeScore(scoreGained);
            } else {
                RhythmGame.displayMessage("Bad Hit!");
                RhythmGame.changeScore(-5);
                // show miss message
            }
        }
    }
}
