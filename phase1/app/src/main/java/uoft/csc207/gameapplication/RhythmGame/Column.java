package uoft.csc207.gameapplication.RhythmGame;

import java.util.ArrayList;

import uoft.csc207.gameapplication.GameActivity;

/**
 * A column of the rhythm, which consists of a shadow (the target)
 * and the notes to hit within the column.
 */
class Column {
    private int height = 100;

    private Target target;
    private ArrayList<Note> notes;

    Column(int height) {
        this.height = height;
        this.target = new Target(10, 5);
        notes = new ArrayList<>();
    }

    void update() {
        ArrayList<Note> notesCopy = new ArrayList<>(notes);
        for (Note note : notesCopy) {
            note.moveUp(1);

            // Removes off-screen notes
            if (note.getY() > height || note.getY() < -height / 4) {
                notes.remove(note);
                RhythmGame.changeScore(-1);
            }
        }

    }

    private boolean checkLowestNote() {
        int lowest = 0;
        for (Note note : notes) {
            if (note.getY() > lowest) {
                lowest = note.getY();
            }
        }
        // screen height = 100, note height = 20??

        return this.height - lowest > this.height / 4;

    }

    void generateNote() {
        if (checkLowestNote()) {
            notes.add(new Note(height));
        }
    }

    void tap() {
        // check to see if top note is in target

        // adds new note
        //        notes.add(new Note(50));

        // if in target remove note and update points

        ArrayList<Note> notesCopy = new ArrayList<>(notes);
        for (int i = 0; i < notesCopy.size(); i++) {
            if (target.contains(notes.get(i))) {
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
                if (i < notesCopy.size() - 1 && !target.contains(notesCopy.get(i + 1))) break;
            } else {
                RhythmGame.displayMessage("Bad Hit!");
                RhythmGame.changeScore(-5);
                // show miss message
            }
        }
    }

    Target getTarget() {
        return target;
    }

    ArrayList<Note> getNotes() {
        return notes;
    }
}
