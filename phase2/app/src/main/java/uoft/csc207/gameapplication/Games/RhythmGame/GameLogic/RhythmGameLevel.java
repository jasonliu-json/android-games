package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

import android.content.Context;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Column;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.ColumnMessage;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Note;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Target;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator.NoteGenerator;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator.RandomNoteGenerator;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator.SongNoteGenerator;


/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps the target. */
public class RhythmGameLevel extends Observable {
    private int numColumns;
    private int gameHeight;
    private Column[] columns;

    private RhythmGamePointsSystem pointsSystem;
    private NoteGenerator noteGenerator;
    private String song;

    private EndRhythmLevelStrategy endLevelChecker;
//    private boolean gameIsOver = false;
    public static String LEVEL_OVER_MESSAGE = "Level is over.";
//    private static final Map<String, NoteGenerator> NOTE_GENERATORS = createNoteGeneratorsMap();

    /**
     * Constructs the Rhythm game
     *
     * @param numColumns number of columns of the game
     */
    public RhythmGameLevel(int numColumns, int gameHeight, String song, String mode, Context context) {
        this.numColumns = numColumns;
        this.gameHeight = gameHeight;
        this.song = song;
        pointsSystem = new RhythmGamePointsSystem();

        // Creates each column of the game
        columns = new Column[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columns[i] = new Column(gameHeight, i, pointsSystem);
        }

        if (mode.equalsIgnoreCase("RANDOM")) {
            this.noteGenerator = new RandomNoteGenerator(columns, pointsSystem);
            this.endLevelChecker = new EndByLives(10, pointsSystem);
        } else if (mode.equalsIgnoreCase("SONG")) {
            this.noteGenerator = new SongNoteGenerator(columns, song, context);
            this.endLevelChecker = new EndBySong(noteGenerator, columns);
        }
    }

    /**
     * Updates the game by one unit time.
     */
    public void timeUpdate() {
        for (int i = 0; i < numColumns; i++) {
            columns[i].timeUpdate();
        }

        noteGenerator.timeUpdate();

        if (endLevelChecker.getIsLevelOver()) {
            setChanged();
            notifyObservers(LEVEL_OVER_MESSAGE);
        }
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

    public int getNumColumns() {
        return numColumns;
    }

    public int getGameHeight() {
        return gameHeight;
    }

//    Column[] getColumns() {
//        return columns;
//    }

    public String getSong() {
        return song;
    }

    public RhythmGamePointsSystem getPointsSystem() {
        return pointsSystem;
    }

//    public int getNumMissed() {
//        return pointsSystem.getNumMissed();
//    }

    public int getPoints() {
        return pointsSystem.getPoints();
    }

    public boolean getGameIsOver() {
        return endLevelChecker.getIsLevelOver();
    }
}