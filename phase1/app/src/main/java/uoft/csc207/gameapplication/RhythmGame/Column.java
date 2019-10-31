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
        this.target = new Target(10, 5);
        notes = new ArrayList<>();
    }

    Pair<Integer, Integer> update() {
        ArrayList<Note> notesCopy = new ArrayList<>(notes);
        int numMissed = 0;
        for (Note note : notesCopy) {
            note.moveUp(1);

            // Removes off-screen notes
            if (note.getY() > height || note.getY() < - height / 4) {
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
        // screen height = 100, note height = 20??

        return this.height - lowest > this.height / 4;

    }

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
    Pair<String, Integer> tap() {
        int pointsGained = 0;
        String displayMessage = "";
        ArrayList<Note> notesCopy = new ArrayList<>(notes);

        for (int i = 0; i < notesCopy.size(); i++) {
            if (target.contains(notes.get(i))) {
                // score gained is based on the difference between hit position and target, for
                // maximum of 10 points per hit.
                int distFromTarget = Math.abs(target.getY() - notes.get(i).getY());

                pointsGained += 2*(target.getAllowedError() - distFromTarget);

                if (distFromTarget < target.getAllowedError() / (float) 3) {
                    displayMessage = "Perfect!";
                } else if (distFromTarget < 2 * target.getAllowedError() / (float) 3) {
                    displayMessage ="Great!";
                } else {
                    displayMessage ="Good!";
                }
                notes.remove(i);
                if (i < notesCopy.size() - 1 && !target.contains(notesCopy.get(i + 1))) break;
            } else if (notes.get(i).getY() > target.getY()) {
                displayMessage ="Bad Hit!";
                pointsGained -= 5;
            }
        }
        this.message = new RhythmGameMessage(displayMessage);
        return new Pair<>(displayMessage, pointsGained);

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
