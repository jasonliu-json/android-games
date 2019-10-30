package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;
import android.util.SparseArray;

import java.util.ArrayList;

import uoft.csc207.gameapplication.GameDriver;

public class RhythmGameDriver extends GameDriver {
    private RhythmGame rhythmGame;
    private int numColumns = 4;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;
    private Paint textPaint;
    private Paint missedTextPaint;
    private Paint messagePaint;

    RhythmGameDriver(Context context) {
        super();
        rhythmGame = new RhythmGame(context, numColumns);
        setTheme();
    }

    public boolean getIsGameOver() {
        return rhythmGame.getIsGameOver();
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

        textPaint = new Paint();
        textPaint.setTextSize(100);

        missedTextPaint = new Paint();
        missedTextPaint.setTextSize(75);
        missedTextPaint.setColor(Color.RED);

        messagePaint = new Paint();
        messagePaint.setTextSize(50);
        messagePaint.setColor(Color.GREEN);
    }

    public void touchStart(float x, float y) {
        // Determines the column number based on the screen width
        int colNumber = (int) (4 * x / screenWidth);
        rhythmGame.tap(colNumber);
    }

    public void touchMove(float x, float y) {
    }

    public void touchUp() {
    }

    void update() {
        rhythmGame.update();
    }

    public void draw(Canvas canvas) {
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);

        int colSize = screenWidth / numColumns;
        float heightRatio = (float) screenHeight / rhythmGame.getGameHeight();

        Target[] targets = rhythmGame.targetsToDraw();
        SparseArray<ArrayList<Note>> notesMap = rhythmGame.notesToDraw();
        RhythmGameMessage[] messages = rhythmGame.messagesToDraw();

//        SparseArray<Pair<ArrayList<Note>, Target>> toDrawMap = rhythmGame.toDraw();
        for (int i = 0; i < numColumns; i++) {
//            Pair<ArrayList<Note>, Target> pair = toDrawMap.get(i);
            NoteShape scalableCopy = colUnitNoteShapes[i].clone();
            float colWidthRatio = colSize / colUnitNoteShapes[i].getWidth();

Target target = targets[i];
//            Target target = pair.second;
            float targetWidthRatio = (float) 0.7;
            float targetScale = targetWidthRatio * colWidthRatio;
            scalableCopy.setScale(targetScale);
            float xTarget = (1 - targetWidthRatio) * colWidthRatio / 2 + i * colSize;
            scalableCopy.draw(newCanvas, xTarget, target.getY() * heightRatio, targetPaint);

            ArrayList<Note> notes = notesMap.get(i);
//            ArrayList<Note> notes = pair.first;
            float noteWidthRatio = (float) 0.6;
            float noteScale = noteWidthRatio * colWidthRatio;
            float xNote = (1 - noteWidthRatio) * colWidthRatio / 2 + i * colSize;
            scalableCopy.setScale(noteScale);
            for (Note note : notes) {
                scalableCopy.draw(newCanvas, xNote, note.getY() * heightRatio, columnPaints[i]);
            }


//            float xMessage = i * colSize + colSize / 2;
            newCanvas.drawText(messages[i].getMessage(), xNote, target.getY() * heightRatio, messagePaint);

        }

        newCanvas.drawText(String.valueOf(rhythmGame.getPoints()), 10, 80, textPaint);

        newCanvas.drawText("Missed: " + rhythmGame.getNumNotesMissed(), screenWidth /2, 80, missedTextPaint);

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }

}
