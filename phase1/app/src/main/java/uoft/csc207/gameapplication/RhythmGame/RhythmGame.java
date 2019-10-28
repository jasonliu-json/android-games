package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import android.util.Pair;


// testing git

/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps a fixed note shadow. */
public class RhythmGame {
    private int numColumns = 4;
    private ArrayList<Column> columns;

    /**
     * Constructs the Rhythm game
     *
     * @param numColumns number of columns of the game
     */
    public RhythmGame(int numColumns) {
        this.numColumns = numColumns;

        columns = new ArrayList<>(numColumns);
        for (int i = 0; i < numColumns; i++) {
            columns.add(new Column(100, i));
        }

//        setPointsGained(0);
//        setNumDeaths(0);
    }


    /**
     * Updates the state of the game for the next frame
     */

    public void update() {
        // update columns
        for (Column col : columns) {
            col.update();
        }

        for (int i=0;i<numColumns;i++) {
            // RNG notes for now lol. 25% chance for each column to generate note.
            double randomNumber = Math.random();
            if (randomNumber<0.25){
                columns.get(i).generateNote();
            }
        }

        // update time
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
        canvas.drawCircle(colSize * 2, screenHeight / 2, 20, circlePaint);

        // draw statistics
        // draw time
    }


    public void tap(int colNumber) {
        columns.get(colNumber).tap();
    }
}
