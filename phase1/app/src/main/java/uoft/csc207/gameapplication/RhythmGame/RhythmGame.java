package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;

import java.util.ArrayList;

import android.util.Pair;
import android.util.SparseArray;

import android.widget.Toast;


/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps the target. */
public class RhythmGame {
    private Context context;
    private int gameHeight = 100;
    private int numColumns = 4;
    private Column[] columns;

    public static int score;
    public static String difficulty;
    public static long runTime = 200000;
    public static long updateInterval = 100;
    public static double notesFrequency = 0.05;


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

//        setPointsGained(0);
//        setNumDeaths(0);
    }

    public static void setDifficulty(String diff) {
        difficulty = diff;
        if (diff.equals("EASY")) {
            updateInterval = 200;
            notesFrequency = 2;
        } else if (diff.equals("HARD")) {
            updateInterval = 50;
            notesFrequency = 0.1;
        } else {
            updateInterval = 100;
            notesFrequency = 0.05;
        }

    }


    /**
     * Updates the state of the game for the next frame
     */
//
//    public void randomlyGenerateNotes(Column col) {
//
//        if (col.checkLowestNote()) {
//            double randomNumber = Math.random();
//            if (randomNumber < 0.1) {
//                col.generateNote();
//            }
//        }
//    }

    public void update() {
        for (Column col : columns) {
            col.update();
            double randomNumber = Math.random();
            if (randomNumber < notesFrequency) {
                col.generateNote();
            }
        }

        // update time
    }

    static void changeScore(int amount) {
        score += amount;
    }

    public static String getScore(){
        String s = "Score:  ";
        return s + Integer.toString(score);
    }

    static void displayMessage(String message) {
        // something about Toast should go here, will implement later
//        RhythmGameView.displayMessage(message);
        System.out.println(message);
    }


    void tap(int colNumber) {
        columns[colNumber].tap();
    }

    SparseArray<Pair<ArrayList<Note>, Target>> toDraw() {
        SparseArray<Pair<ArrayList<Note>, Target>> map = new SparseArray<>();
        for (int i = 0; i <numColumns; i++) {
            Pair<ArrayList<Note>, Target> pair = new Pair<>(new ArrayList<>(columns[i].getNotes()),
                    columns[i].getTarget());
            map.put(i, pair);
        }
        return map;
    }

    int getGameHeight() {
        return gameHeight;
    }
}
