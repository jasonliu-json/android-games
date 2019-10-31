package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import android.media.MediaPlayer;
import android.provider.MediaStore;
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

    private int numNotesMissed = 0;
    private int lives = 5000;
    private int noteGenerationPeriod = 1000;

    public enum Difficulty { EASY, NORMAL, HARD, IMPOSSIBLE}

    private long startTime;
    private static int refreshTime = 500;
    private MediaPlayer mediaPlayer1;
//    private MediaPlayer mediaPlayer2;
//    private RhythmGameMessage rhythmGameMessage = new RhythmGameMessage("");
    private MediaPlayer hitSound;

    private int points = 0;
    private int numDeaths = 0;
    private HashMap<String, Integer> stats;
    // keys: hit type, values: number of times

    private boolean isGameOver = false;


    /**
     * Constructs the Rhythm game
     *
     * @param numColumns number of columns of the game
     */
    public RhythmGame(Context context, int numColumns) {

        this.context = context;
        this.numColumns = numColumns;

        columns = new Column[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columns[i] = new Column(gameHeight);
        }

        setDifficulty(Difficulty.IMPOSSIBLE);
        mediaPlayer1 = MediaPlayer.create(context, R.raw.old_town_road);
        mediaPlayer1.start();
//        mediaPlayer2 = MediaPlayer.create(context, R.raw.old_town_road);
        hitSound = MediaPlayer.create(context, R.raw.note_hit_sound);

        startTime = System.currentTimeMillis();

        stats = new HashMap<>();
        stats.put("Perfect!", 0);
        stats.put("Great!", 0);
        stats.put("Good!", 0);
        stats.put("Bad Hit!", 0);

//        setPointsGained(0);
//        setNumDeaths(0);
    }

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
                noteGenerationPeriod = 900;
                break;
        }
    }

    public static int getRefreshTime() {return refreshTime;}

    public void update() {
        double randomNumber = Math.random();
        for (int i = 0; i < numColumns; i++) {
            Pair<Integer, Integer> pair = columns[i].update();
            addPoints(pair.first);
            numNotesMissed += pair.second;
        }

        if (System.currentTimeMillis() - startTime >= noteGenerationPeriod) {
            columns[(int) (4 * randomNumber)].generateNote();
            startTime = System.currentTimeMillis();
        }

        if (getPoints() > 100) setDifficulty(Difficulty.HARD);
        else if (getPoints() > 50) setDifficulty(Difficulty.NORMAL);

        if (numNotesMissed >= lives) {
            gameOver();
        }
    }

    public void gameOver() {
        setIsGameOver(true);
        mediaPlayer1.stop();
        mediaPlayer1.release();
        mediaPlayer1 = null;
    }

    void tap(int colNumber) {
        if (!getIsGameOver()) {
            int scoreChange = columns[colNumber].tap();
            if (scoreChange > 0) {
                hitSound.start();
            }

            addPoints(scoreChange);
            String hitType = columns[colNumber].getMessage().getMessage();
            if (stats.get(hitType) != null) {
                stats.put(hitType, stats.get(hitType) + 1);
        System.out.println(hitType);
        System.out.println(stats.get(hitType));
            }
        }
    }

    RhythmGameMessage[] messagesToDraw() {
        RhythmGameMessage[] messages = new RhythmGameMessage[numColumns];
        for (int i = 0; i < numColumns; i++) messages[i] = columns[i].getMessage();
        return messages;
    }

    Target[] targetsToDraw() {
        Target[] targets = new Target[numColumns];
        for (int i = 0; i < numColumns; i++) targets[i] = columns[i].getTarget();
        return targets;
    }

    SparseArray<ArrayList<Note>> notesToDraw() {
        SparseArray<ArrayList<Note>> notesMap = new SparseArray<>();
        for (int i = 0; i < numColumns; i++) notesMap.put(i, columns[i].getNotes());
        return notesMap;
    }
//    SparseArray<Pair<ArrayList<Note>, Target>> toDraw() {
//        SparseArray<Pair<ArrayList<Note>, Target>> map = new SparseArray<>();
//        for (int i = 0; i <numColumns; i++) {
//            Pair<ArrayList<Note>, Target> pair = new Pair<>(new ArrayList<>(columns[i].getNotes()),
//                    columns[i].getTarget());
//            map.put(i, pair);
//        }
//        return map;
//    }

    int getGameHeight() {
        return gameHeight;
    }

    int getNumNotesMissed() {
        return numNotesMissed;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int dPoints) {
        // points cannot go below 0.
        this.points = Math.max(this.points + dPoints, 0);
    }
}
