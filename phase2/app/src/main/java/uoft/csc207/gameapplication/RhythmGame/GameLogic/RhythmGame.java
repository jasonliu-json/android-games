package uoft.csc207.gameapplication.RhythmGame.GameLogic;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import android.media.MediaPlayer;
import android.util.SparseArray;

import uoft.csc207.gameapplication.GameLogic;
import uoft.csc207.gameapplication.R;


/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps the target. */
public class RhythmGame extends GameLogic {
    private Context context;
    private int gameHeight = 100;
    private int numColumns = 4;
    private Column[] columns;

    private long startTime;
    private static int refreshTime = 500;
    private MediaPlayer mediaPlayer;

    // Key: hit type, Value: number of times
    private RhythmGamePointsSystem pointsSystem;
    private NoteIntervals noteIntervals;

    private ArrayList<Long> intervalsArray;

    //    private HashMap<String, Integer> stats;
//    private int points = 0;
//    private int numNotesMissed = 0;
    private int lives = 10;
    private int noteGenerationPeriod = 1000;

    public int getNumColumns() {
        return numColumns;
    }

    public enum Difficulty { EASY, NORMAL, HARD, IMPOSSIBLE}

    private boolean isGameOver = false;

    /**
     * Constructs the Rhythm game
     *
     * @param numColumns number of columns of the game
     */
    public RhythmGame(Context context, int numColumns) {
        this.context = context;
        this.numColumns = numColumns;
        pointsSystem = new RhythmGamePointsSystem();
//        noteIntervals = new NoteIntervals("Old Town Road");
//        mediaPlayer = createMediaPlayer("Mii Channel");

        setSong("Mii Channel");


        // Creates each column of the game
        columns = new Column[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columns[i] = new Column(gameHeight, i, pointsSystem);
        }

        setDifficulty(Difficulty.EASY);
        start();
    }

    public void start() {
        // Starts song
//        mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
        mediaPlayer.start();

        startTime = System.currentTimeMillis();
    }

    public void stop() {

        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
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

    private void setSong(String song) {
        switch (song) {
            case "Old Town Road":
                mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
                intervalsArray = noteIntervals.generateIntervalsArray("oldTownRoadIntervals.csv");
            case "Mii Channel":
                mediaPlayer = MediaPlayer.create(context, R.raw.mii_channel);
                intervalsArray = noteIntervals.generateIntervalsArray("miiChannelIntervals.csv");
            default:
                mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
                intervalsArray = noteIntervals.generateIntervalsArray("oldTownRoadIntervals.csv");
        }
    }

//    private MediaPlayer createMediaPlayer(String song) {
//        switch (song) {
//            case "Old Town Road":
//                return MediaPlayer.create(context, R.raw.old_town_road);
//            case "Mii Channel":
//                return MediaPlayer.create(context, R.raw.mii_channel);
//            default:
//                return MediaPlayer.create(context, R.raw.old_town_road);
//        }
//    }

//    private ArrayList<Long> generateIntervalsArray(String song) {
//        switch (song) {
//            case "Old Town Road":
//                return noteIntervals.generateIntervalsArray("oldTownRoadIntervals.csv");
//            case "Mii Channel":
//                return noteIntervals.generateIntervalsArray("miiChannelIntervals.csv");
//            default:
//                return noteIntervals.generateIntervalsArray("oldTownRoadIntervals.csv");
//        }
//    }

//    private MediaPlayer createMediaPlayer(String song) {
//        switch (song) {
//            case "Old Town Road":
//                return MediaPlayer.create(context, R.raw.old_town_road);
//            case "Mii Channel":
//                return MediaPlayer.create(context, R.raw.mii_channel);
//            default:
//                return MediaPlayer.create(context, R.raw.old_town_road);
//        }
//    }

    /**
     * Updates the game
     */
    public void timeUpdate() {

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
     * Taps the column and updates statistics the games' statistics.
     * @param colNumber the number of the column
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
    public RhythmGameMessage[] getMessages() {
        RhythmGameMessage[] messages = new RhythmGameMessage[numColumns];
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

    public int getGameHeight() {
        return gameHeight;
    }

    public int getNumMissed() {
        return pointsSystem.getNumMissed();
    }

    /**
     * Ends the game.
     */
    private void gameOver() {
        setGameIsOver(true);
        stop();

//        setChanged();
//        notifyObservers("Game is over.");
    }

    boolean getGameIsOver() {
        return isGameOver;
    }

    public void setGameIsOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getPoints() {
        return pointsSystem.getPoints();
    }

    @Override
    public boolean getIsGameOver() {
        return isGameOver;
    }

//    public void setPoints(int points) {
//        this.pointsSystem.setPoints(points);
//    }

    public static int getRefreshTime() {return refreshTime;}
}