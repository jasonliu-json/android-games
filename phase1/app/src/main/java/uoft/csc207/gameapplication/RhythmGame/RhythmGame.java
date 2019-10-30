package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import android.util.Pair;
import android.widget.Toast;


/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps a fixed note shadow. */
public class RhythmGame {
    private int numColumns = 4;
    private ArrayList<Column> columns;
    private Context context;

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

        columns = new ArrayList<>(numColumns);
        for (int i = 0; i < numColumns; i++) {
            columns.add(new Column(100, i));
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

//    for (int i = 0; i < numColumns; i++) {
//      // RNG notes for now lol. 25% chance for each column to generate note.
//      //            double randomNumb     //            } = Math.random();
//      ////            if (randomNumber<0.25){
//      ////                columns.get(i).generateNote();
//      ////            }
//    }

        // update time
    }

    public static void changeScore(int amount) {
        score += amount;
    }

    public static String getScore(){
        String s = "Score:  ";
        return s + Integer.toString(score);
    }

    public static void displayMessage(String message) {
        // something about Toast should go here, will implement later
//        RhythmGameView.displayMessage(message);
        System.out.println(message);
    }




    /**
     * Draws a frame of the game in the current state
     *
     * @param canvas the graphics context to draw on
     */

    public void draw(Canvas canvas, int screenHeight, int screenWidth, NoteShape[] colUnitNoteShapes,
                     Paint[] colPaints, Paint targetPaint) {
        float colSize = screenWidth / (float) numColumns;

        for (int i = 0; i < numColumns; i++) {
            NoteShape colUnitNoteShape = colUnitNoteShapes[i];
            Paint colPaint = colPaints[i];
            columns.get(i).draw(canvas, screenHeight, colSize, colUnitNoteShape, colPaint, targetPaint);
        }

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.YELLOW);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);

        // draw statistics
        // draw time
    }


    public void tap(int colNumber) {
        columns.get(colNumber).tap();
    }
}
