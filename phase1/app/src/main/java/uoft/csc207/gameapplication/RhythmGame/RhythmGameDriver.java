package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;
import android.util.SparseArray;

import java.util.ArrayList;

import uoft.csc207.gameapplication.GameDriver;
import uoft.csc207.gameapplication.MainActivity;

public class RhythmGameDriver extends GameDriver {
    private RhythmGame rhythmGame;
    private int numColumns = 4;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;

    public RhythmGameDriver(Context context) {
        super();
        rhythmGame = new RhythmGame(context, numColumns);
        setTheme();
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
    //    rhythmGame.update();
        newCanvas.save();

        int colSize = screenWidth / numColumns;
        float heightRatio = (float) screenHeight / rhythmGame.getGameHeight();

        Paint tempPaint = new Paint();
        tempPaint.setColor(Color.WHITE);
        newCanvas.drawRect(0, 0, screenWidth, screenHeight, tempPaint);

        SparseArray<Pair<ArrayList<Note>, Target>> toDrawMap = rhythmGame.toDraw();
        for (int i = 0; i < numColumns; i++) {
            Pair<ArrayList<Note>, Target> pair = toDrawMap.get(i);
            NoteShape scalableCopy = colUnitNoteShapes[i].clone();
            float colWidthRatio = colSize / colUnitNoteShapes[i].getWidth();


            Target target = pair.second;
            float targetWidthRatio = (float) 0.7;
            float targetScale = targetWidthRatio * colWidthRatio;
            scalableCopy.setScale(targetScale);
            float xTarget = (1 - targetWidthRatio) * colWidthRatio / 2 + i * colSize;
            scalableCopy.draw(newCanvas, xTarget, target.getY() * heightRatio, targetPaint);

            ArrayList<Note> notes = pair.first;
            float noteWidthRatio = (float) 0.6;
            float noteScale = noteWidthRatio * colWidthRatio;
            float xNote = (1 - noteWidthRatio) * colWidthRatio / 2 + i * colSize;
            scalableCopy.setScale(noteScale);
            for (Note note : notes) {
                scalableCopy.draw(newCanvas, xNote, note.getY() * heightRatio, columnPaints[i]);
            }
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }

}
