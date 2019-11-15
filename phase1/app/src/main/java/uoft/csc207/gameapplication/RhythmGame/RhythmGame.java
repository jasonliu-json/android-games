package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import android.media.MediaPlayer;
import android.util.Pair;
import android.util.SparseArray;

import uoft.csc207.gameapplication.R;

/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps the target. */
public class RhythmGame {
    private Context context;
    private int gameHeight = 100;
    private int numColumns = 4;
    private Column[] columns;

    private long startTime;
    private static int refreshTime = 500;
    private MediaPlayer mediaPlayer;

    // Key: hit type, Value: number of times
    private RhythmGamePointsSystem pointsSystem;
//    private HashMap<String, Integer> stats;
//    private int points = 0;
//    private int numNotesMissed = 0;
    private int lives = 10;
    private int noteGenerationPeriod = 1000;
    public enum Difficulty { EASY, NORMAL, HARD, IMPOSSIBLE}

    private boolean gameIsOver = false;

    /**
     * Constructs the Rhythm game
     *
     * @param numColumns number of columns of the game
     */
    public RhythmGame(Context context, int numColumns) {
        this.context = context;
        this.numColumns = numColumns;
        pointsSystem = new RhythmGamePointsSystem();

        // Creates each column of the game
        columns = new Column[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columns[i] = new Column(gameHeight, i, pointsSystem);
        }

        setDifficulty(Difficulty.EASY);
        // Starts song
        mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
        mediaPlayer.start();

        startTime = System.currentTimeMillis();



//        stats = new HashMap<>();
//        stats.put("Perfect!", 0);
//        stats.put("Great!", 0);
//        stats.put("Good!", 0);
//        stats.put("Bad Hit!", 0);
    }

    /**
     * Sets the difficulty of the game by generating notes more or less frequently
     * @param diff enum RhythmGame.Difficulty
     */
    private void setDifficulty(Difficulty diff) {
        switch(diff) {
            case EASY:
                noteGenerationPeriod = 900;
                break;
            case NORMAL:
                noteGenerationPeriod = 700;
                break;
            case HARD:
                noteGenerationPeriod = 500;
                break;
            case IMPOSSIBLE:
                noteGenerationPeriod = 200;
                refreshTime = 100;
                break;
            default:
                noteGenerationPeriod = 1000;
                break;
        }
    }

    /**
     * Updates the game
     */
    void update() {

        // Updates points based on missed notes
        for (int i = 0; i < numColumns; i++) {
            columns[i].update();
        }

        // Every period generate a note at a random column
        double randomNumber = Math.random();
        if (System.currentTimeMillis() - startTime >= noteGenerationPeriod) {
            columns[(int) (4 * randomNumber)].generateNote();
            startTime = System.currentTimeMillis();
        }

        // Changes difficulty based on points
        if (getPoints() > 100) setDifficulty(Difficulty.HARD);
        else if (getPoints() > 50) setDifficulty(Difficulty.NORMAL);

        if (getNumMissed() >= lives) {
            gameOver();
        }
    }

    /**
     * Ends the game.
     */
    private void gameOver() {
        setGameIsOver(true);
        // Ensures memory is released
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    /**
     * Taps the column and updates statistics the games' statistics.
     * @param colNumber the number of the column
     */
    void tap(int colNumber) {
        if (!getGameIsOver()) {
            columns[colNumber].tap();
        }
    }

    /**
     * Returns a list of messages, one in each column
     * @return a list of RhythmGameMessages where the index is the column number
     */
    RhythmGameMessage[] messagesToDraw() {
        RhythmGameMessage[] messages = new RhythmGameMessage[numColumns];
        for (int i = 0; i < numColumns; i++) messages[i] = columns[i].getMessage();
        return messages;
    }

    /**
     * Returns a list of targets, one in each column
     * @return a list of Targets where the index is the column number
     */
    Target[] targetsToDraw() {
        Target[] targets = new Target[numColumns];
        for (int i = 0; i < numColumns; i++) targets[i] = columns[i].getTarget();
        return targets;
    }

    /**
     * Returns a list of a list of notes, one list in each column
     * @return a sparseArray, where the key is the column number
     */
    SparseArray<ArrayList<Note>> notesToDraw() {
        SparseArray<ArrayList<Note>> notesMap = new SparseArray<>();
        for (int i = 0; i < numColumns; i++) notesMap.put(i, columns[i].getNotes());
        return notesMap;
    }

    int getGameHeight() {
        return gameHeight;
    }

    int getNumMissed() {
        return pointsSystem.getNumMissed();
    }

    boolean getGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameOver) {
        gameIsOver = gameOver;
    }

    int getPoints() {
        return pointsSystem.getPoints();
    }

//    public void setPoints(int points) {
//        this.pointsSystem.setPoints(points);
//    }

    public static int getRefreshTime() {return refreshTime;}
}
