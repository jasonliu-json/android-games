package uoft.csc207.gameapplication.RhythmGame.GameLogic;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * A column of the rhythm, which consists of a shadow (the target)
 * and the notes to hit within the column.
 */
class Column extends Observable {
    private int height = 100;
    private int id;

    private Target target;
    private ArrayList<Note> notes;

    private RhythmGameMessage message = new RhythmGameMessage("");

    Column(int height, int id, Observer observer) {
        this.height = height;
        this.id = id;
        this.target = new Target(height/5, height/20);
        notes = new ArrayList<>();

//        for (Observer observer : observers) {
            this.addObserver(observer);
//        }
    }

    /**
     * Updates the state of the game.
     */
    void update() {
        ArrayList<Note> notesCopy = new ArrayList<>(notes);

        // moves each not up by one
        for (Note note : notesCopy) {
            note.moveUp(1);

            // Removes off-screen notes
            if (note.getY() < 0) {
                notes.remove(note);
                setChanged();
                notifyObservers(RhythmGamePointsSystem.NoteEvent.MISSED);
            }
        }

        if (!message.getMessage().equals("")) {
            message.incrementNumIterationsExisted();
            if (message.getNumIterExisted() >= 1000) message = new RhythmGameMessage("");
        }
    }

    private boolean checkLowestNote() {
        int lowest = 0;
        for (Note note : notes) {
            if (note.getY() > lowest) {
                lowest = note.getY();
            }
        }

        return this.height - lowest > 2 * target.getAllowedError();

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
     */
    void tap() {
        setChanged();
        ArrayList<Note> notesCopy = new ArrayList<>(notes);
        for (int i = 0; i < notesCopy.size(); i++) {
            if (notes.get(i).getY() > target.getY() + target.getAllowedError()) break;
            if (target.contains(notes.get(i))) {
                // score gained is based on the difference between hit position and target, for
                // maximum of 10 points per hit.
                int distFromTarget = Math.abs(target.getY() - notes.get(i).getY());

                // Determines the accuracy of the tap
                if (distFromTarget < target.getAllowedError() / (float) 3) {
                    notifyObservers(RhythmGamePointsSystem.NoteEvent.PERFECT);
                    this.message = new RhythmGameMessage("Perfect!");
                } else if (distFromTarget < 2 * target.getAllowedError() / (float) 3) {
                    notifyObservers(RhythmGamePointsSystem.NoteEvent.GREAT);
                    this.message = new RhythmGameMessage("Great!");
                } else {
                    notifyObservers(RhythmGamePointsSystem.NoteEvent.GOOD);
                    this.message = new RhythmGameMessage("Good!");
                }

                // Removes the note contained
                notes.remove(i);

                // Does not check any other note in the column
                if (i < notesCopy.size() - 1 && !target.contains(notesCopy.get(i + 1))) return;
            }
        }

        notifyObservers(RhythmGamePointsSystem.NoteEvent.BAD);
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

    public int getId() {
        return id;
    }
}
