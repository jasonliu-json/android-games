package uoft.csc207.gameapplication.RhythmGame;

import android.util.Pair;

import java.util.ArrayList;


/**
 * A column of the rhythm, which consists of a shadow (the target)
 * and the notes to hit within the column.
 */
class Column {
    private int height = 100;

    private Target target;
    private ArrayList<Note> notes;

    private RhythmGameMessage message = new RhythmGameMessage("");

    Column(int height) {
        this.height = height;
        this.target = new Target(20, 5);
        notes = new ArrayList<>();
    }

    /**
     * Updates the state of the game.
     * @return the amount of points to change (first) and number of notes missed (second)
     */
    Pair<Integer, Integer> update() {
        ArrayList<Note> notesCopy = new ArrayList<>(notes);
        int numMissed = 0;

        // moves each not up by one
        for (Note note : notesCopy) {
            note.moveUp(1);

            // Removes off-screen notes
            if (note.getY() > height || note.getY() < 0) {
                notes.remove(note);
                numMissed += 1;
            }
        }

        if (!message.getMessage().equals("")) {
            message.incrementNumIterationsExisted();
            if (message.getNumIterExisted() >= 10) message = new RhythmGameMessage("");
        }

        return new Pair(-numMissed, numMissed);

    }

    private boolean checkLowestNote() {
        int lowest = 0;
        for (Note note : notes) {
            if (note.getY() > lowest) {
                lowest = note.getY();
            }
        }

        return this.height - lowest > this.height / 4;

    }

    /**
     * Generates a note at the bottom of the column.
     */
    void generateNote() {
        if (checkLowestNote()) {
            notes.add(new Note(height));
        }
    }

    /**
     * Checks if any notes are in the target.
     * Pre-condition: the notes are sorted in ascending order of y-value.
     * @return the number of points gained
     */
    int tap() {
        int pointsGained = 0;
        if (notes.size() == 0) {
            this.message = new RhythmGameMessage("Bad Hit!");
            pointsGained = -5;
            return pointsGained;
        }

        ArrayList<Note> notesCopy = new ArrayList<>(notes);
        for (int i = 0; i < notesCopy.size(); i++) {
            if (target.contains(notes.get(i))) {
                // score gained is based on the difference between hit position and target, for
                // maximum of 10 points per hit.
                int distFromTarget = Math.abs(target.getY() - notes.get(i).getY());

                pointsGained += 2*(target.getAllowedError() - distFromTarget);

                // Determines the accuracy of the tap
                if (distFromTarget < target.getAllowedError() / (float) 3) {
                    this.message = new RhythmGameMessage("Perfect!");
                } else if (distFromTarget < 2 * target.getAllowedError() / (float) 3) {
                    this.message = new RhythmGameMessage("Great!");
                } else {
                    this.message = new RhythmGameMessage("Good!");
                }

                // Removes the note contained
                notes.remove(i);

                // Does not check any other note in the column
                if (i < notesCopy.size() - 1 && !target.contains(notesCopy.get(i + 1))) break;
            } else if (notes.get(i).getY() > target.getY()) {
                this.message = new RhythmGameMessage("Bad Hit!");
                pointsGained -= 5;
                // show miss message
            }
        }
        return pointsGained;
    }

    Target getTarget() {
        return target;
    }

    ArrayList<Note> getNotes() {
        return notes;
    }

    RhythmGameMessage getMessage() {
        return message;
    }
}
