package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;

import java.util.ArrayList;

import android.media.MediaPlayer;
import android.util.Pair;
import android.util.SparseArray;

import android.widget.Switch;
import android.widget.Toast;

import uoft.csc207.gameapplication.R;
import uoft.csc207.gameapplication.SubGame;


/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps the target. */
public class RhythmGame extends SubGame {
    private Context context;
    private int gameHeight = 100;
    private int numColumns = 4;
    private Column[] columns;

    private int numNotesMissed = 0;
    private int noteGenerationPeriod = 1000;

    public enum Difficulty { EASY, NORMAL, HARD}

    private long startTime;

    private MediaPlayer mediaPlayer;

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

        mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
        mediaPlayer.start();
        startTime = System.currentTimeMillis();
        setDifficulty(Difficulty.EASY);

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
            default:
                noteGenerationPeriod = 900;
                break;
        }
    }

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

        if (numNotesMissed >= 5) {
            setIsGameOver(true);
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    void tap(int colNumber) {
        addPoints(columns[colNumber].tap());
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
}
