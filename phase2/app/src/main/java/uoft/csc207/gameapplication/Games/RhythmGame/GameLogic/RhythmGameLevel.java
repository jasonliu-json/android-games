package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps the target. */
public abstract class RhythmGameLevel extends Observable {
    private int numColumns;
    private int gameHeight;
    private Column[] columns;

    private RhythmGamePointsSystem pointsSystem;
    private NoteGenerator noteGenerator;
    private String song;

    private boolean gameIsOver = false;
    public static String LEVEL_OVER_MESSAGE = "Level is over.";

    /**
     * Constructs the Rhythm game
     *
     * @param numColumns number of columns of the game
     */
    public RhythmGameLevel(int numColumns, int gameHeight, String song) {
        this.numColumns = numColumns;
        this.gameHeight = gameHeight;
        this.song = song;
//        this.noteGenerator = getNoteGenerator();

        pointsSystem = new RhythmGamePointsSystem();
        // Creates each column of the game
        columns = new Column[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columns[i] = new Column(gameHeight, i, pointsSystem);
        }
    }

    /**
     * Updates the game by one unit time.
     */
    public void timeUpdate() {
        for (int i = 0; i < numColumns; i++) {
            columns[i].timeUpdate();
        }

        noteGenerator.timeUpdate(columns);
    }

    public void start() {
        noteGenerator.start();
    }

    public void stop() {
        noteGenerator.stop();
    }

    /**
     * Taps the column, when the player taps that column
     * @param colNumber the column id
     */
    public void tap(int colNumber) {
        if (!getGameIsOver()) {
            columns[colNumber].tap();
        }
    }

    /**
     * Ends the game.
     */
    void gameOver() {
        setGameIsOver(true);

        setChanged();
        notifyObservers(LEVEL_OVER_MESSAGE);
    }

    /**
     * Returns a list of messages, one in each column
     * @return a list of RhythmGameMessages where the index is the column number
     */
    public ColumnMessage[] getMessages() {
        ColumnMessage[] messages = new ColumnMessage[numColumns];
        for (int i = 0; i < numColumns; i++) messages[i] = columns[i].getMessage();
        return messages;
    }

    /**
     * Returns a list of targets, one in each column
     * @return a list of Targets where the index is the column number
     */
    public Target[] getAllTargets() {
        Target[] targets = new Target[numColumns];
        for (int i = 0; i < numColumns; i++) targets[i] = columns[i].getTarget();
        return targets;
    }

    /**
     * Returns a list of a list of notes, one list in each column
     * @return a sparseArray, where the key is the column number
     */
    public SparseArray<List<Note>> getAllNotes() {
        SparseArray<List<Note>> notesMap = new SparseArray<>();
        for (int i = 0; i < numColumns; i++) {
            List<Note> copy = new ArrayList<>(columns[i].getNotes());
            notesMap.put(i, copy);
        }
        return notesMap;
    }

    void setNoteGenerator(NoteGenerator noteGenerator) {
        this.noteGenerator = noteGenerator;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    Column[] getColumns() {
        return columns;
    }

    public String getSong() {
        return song;
    }

    public RhythmGamePointsSystem getPointsSystem() {
        return pointsSystem;
    }

    public int getNumMissed() {
        return pointsSystem.getNumMissed();
    }

    public int getPoints() {
        return pointsSystem.getPoints();
    }

    private void setGameIsOver(boolean gameOver) {
        gameIsOver = gameOver;
    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }
}