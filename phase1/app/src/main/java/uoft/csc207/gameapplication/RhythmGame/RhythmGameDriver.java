package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.SparseArray;

import java.util.ArrayList;

import uoft.csc207.gameapplication.GameDriver;

/**
 * The driver for Rhythm Game.
 */
public class RhythmGameDriver extends GameDriver {
    private RhythmGame rhythmGame;
    private int numColumns = 4;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;
//    private Paint textPaint;
    private Paint missedTextPaint;
    private Paint messagePaint;

    float colSize;
    float colWidthRatio;
    float targetWidthRatio;
    float targetScale;
    float noteWidthRatio;
    float noteScale;
    float heightRatio;

    public RhythmGameDriver(Context context) {
        super();
        rhythmGame = new RhythmGame(context, numColumns);
        setTheme();
    }


    @Override
    public void init(DisplayMetrics metrics) {
        super.init(metrics);

        colSize = screenWidth / (float) numColumns;
        colWidthRatio = colSize / 2;

        // target width is 70% the width of the column
        targetWidthRatio = (float) 0.7;
        targetScale = (float) 0.7 * colWidthRatio;      // the new size of one unit length for target

        // note width is 60% the width of the column
        noteWidthRatio = (float) 0.6;
        noteScale = (float) 0.6 * colWidthRatio;    // the new size of one unit length for note

        // add 3 times note scale, so when the note reaches 0,
        // the graphic representation of the note is completely offscreen
        heightRatio = (float) (screenHeight + 3 * noteScale) / rhythmGame.getGameHeight();
        bitmap = Bitmap.createBitmap(screenWidth,
                screenHeight + 3 * (int) Math.ceil(noteScale), Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);
    }

    /**
     * Sets up the paints and the shapes of the notes.
     */
    private void setTheme() {
        columnPaints = new Paint[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columnPaints[i] = new Paint();
            columnPaints[i].setColor(Color.BLUE);
        }

        targetPaint = new Paint();
        targetPaint.setColor(Color.GRAY);

        colUnitNoteShapes = new NoteShape[numColumns];
        int[][] lShape = {{0, 0}, {0, 1}, {0, 2}, {1, 2}};
        int[][] rShape = {{1, 0}, {1, 1}, {1, 2}, {0, 2}};
        int[][] oShape = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};
        int[][][] coords = {rShape, oShape, oShape, lShape};
        for (int i = 0; i < numColumns; i++) {
            Tetromino tetromino = new Tetromino(coords[i]);
            TetrominoShape tetrominoShape = new TetrominoShape(tetromino);
            colUnitNoteShapes[i] = new NoteShape(tetrominoShape);
        }

//        textPaint = new Paint();
//        textPaint.setTextSize(100);

        missedTextPaint = new Paint();
        missedTextPaint.setTextSize(75);
        missedTextPaint.setColor(Color.RED);

        messagePaint = new Paint();
        messagePaint.setTextSize(50);
        messagePaint.setColor(Color.GREEN);
    }

    /**
     * Taps the game.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public void touchStart(float x, float y) {
        // Determines the column number based on the screen width
        int colNumber = (int) (4 * x / screenWidth);
        rhythmGame.tap(colNumber);
    }

    public void touchMove(float x, float y) {
    }

    public void touchUp() {
    }

    /**
     * Update the game state.
     */
    void update() {
        rhythmGame.update();
    }

    /**
     * Draws the game to canvas.
     * @param canvas the canvas to draw on.
     */
    public void draw(Canvas canvas) {
        rhythmGame.update();
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);

        Target[] targets = rhythmGame.targetsToDraw();
        SparseArray<ArrayList<Note>> notesMap = rhythmGame.notesToDraw();
        RhythmGameMessage[] messages = rhythmGame.messagesToDraw();

        for (int i = 0; i < numColumns; i++) {
            NoteShape scalableCopy = colUnitNoteShapes[i].clone();

            Target target = targets[i];
            scalableCopy.setScale(targetScale);
            // centre the target in the column
            float xTarget = (1 - targetWidthRatio) * colWidthRatio / 2 + i * colSize;
            xTarget = i * colSize + (float) 0.5 * colSize - targetScale;
            scalableCopy.draw(newCanvas, xTarget, target.getY() * heightRatio, targetPaint);

            ArrayList<Note> notes = notesMap.get(i);
            scalableCopy.setScale(noteScale);
            // centre the target in the column
            float xNote = (1 - noteWidthRatio) * colWidthRatio / 2 + i * colSize;
            xNote = i * colSize + (float) 0.5 * colSize - noteScale;
            for (Note note : notes) {
                scalableCopy.draw(newCanvas, xNote, note.getY() * heightRatio, columnPaints[i]);
            }

            newCanvas.drawText(messages[i].getMessage(), xNote, target.getY() * heightRatio, messagePaint);
//            if (getGameIsOver()) {
//                String gameOverText = "Game Over :(  Score: " + rhythmGame.getPoints();
//                newCanvas.drawText(gameOverText, screenWidth/2, screenHeight/2, messagePaint);
//            }
        }


        newCanvas.drawText("Missed: " + rhythmGame.getNumNotesMissed(), screenWidth /2,
                80+ 3 *noteScale, missedTextPaint);

        canvas.drawBitmap(bitmap, 0, -3*noteScale, null);
        newCanvas.restore();
    }

    public boolean getGameIsOver() {
        return rhythmGame.getGameIsOver();
    }

    public int getPoints() {
        return rhythmGame.getPoints();
    }

}
