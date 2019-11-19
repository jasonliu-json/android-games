package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.SparseArray;

import java.util.List;

import uoft.csc207.gameapplication.GamePresenter;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.Note;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameMessage;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.Target;

/**
 * How the game is represented on screen.
 */
class RhythmGamePresenter extends GamePresenter {

    private RhythmGame rhythmGame;
    private int numColumns;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;
    private Paint missedTextPaint;
    private Paint messagePaint;

    /* Scaling and Sizing variables */
    private float colSize;
    private float targetScale;
    private float noteScale;
    private float heightRatio;


    public RhythmGamePresenter(RhythmGame rhythmGame) {
        this.rhythmGame = rhythmGame;
        this.numColumns = rhythmGame.getNumColumns();
        setTheme();
    }

    public void init(int screenWidth, int screenHeight) {
        super.init(screenWidth, screenHeight);

        colSize = getScreenWidth() / (float) numColumns;
        float colWidthRatio = colSize / 2; // Since each piece is 2 units wide

        // target width is 70% the width of the column
        targetScale = (float) 0.7 * colWidthRatio;      // the new size of one unit length for target

        // note width is 60% the width of the column
        noteScale = (float) 0.6 * colWidthRatio;    // the new size of one unit length for note

        // add 3 times note scale, so when the note reaches 0,
        // the graphic representation of the note is completely offscreen
        heightRatio = (screenHeight + 3 * noteScale) / rhythmGame.getGameHeight();
        setBitmap(Bitmap.createBitmap(screenWidth,
                screenHeight + 3 * (int) Math.ceil(noteScale), Bitmap.Config.ARGB_8888));
    }

    /* Sets up the paints and the shapes of the notes.
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


        missedTextPaint = new Paint();
        missedTextPaint.setTextSize(75);
        missedTextPaint.setColor(Color.RED);

        messagePaint = new Paint();
        messagePaint.setTextSize(50);
        messagePaint.setColor(Color.GREEN);
    }

    private float getBitmapTop() {return -3 * noteScale;}

    public void  draw(Canvas canvas){
        Canvas newCanvas = getBitCanvas();

        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);

        Target[] targets = rhythmGame.getAllTargets();
        SparseArray<List<Note>> notesMap = rhythmGame.getAllNotes();
        RhythmGameMessage[] messages = rhythmGame.getMessages();

        // draws the target, the notes, and the messages in each column
        for (int i = 0; i < numColumns; i++) {
            NoteShape scalableCopy = colUnitNoteShapes[i].clone();

            drawTarget(newCanvas, targets[i], scalableCopy, i);

            List<Note> notes = notesMap.get(i);
            drawNotes(newCanvas, notes, scalableCopy, i);

            newCanvas.drawText(messages[i].getMessage(), i * colSize, 20 * heightRatio, messagePaint);
        }

        newCanvas.drawText("Missed: " + rhythmGame.getNumMissed(), getScreenWidth() /2,
                80+ 3 *noteScale, missedTextPaint);

        canvas.drawBitmap(getBitmap(),(float) 0, getBitmapTop(), null);
        newCanvas.restore();
    }

    private void drawTarget(Canvas bitCanvas, Target target, NoteShape scalableShape, int colId) {
        scalableShape.setScale(targetScale);
        // centre the target in the column
        float xTarget = (float) (colId + 0.5) * colSize - targetScale;
        scalableShape.draw(bitCanvas, xTarget, target.getY() * heightRatio, targetPaint);
    }

    private void drawNotes(Canvas bitCanvas, List<Note> notes, NoteShape scalableShape, int colId) {
        scalableShape.setScale(noteScale);
        // centre the note in the column
        float xNote = (float) (colId + 0.5) * colSize - noteScale;
        for (Note note : notes) {
            scalableShape.draw(bitCanvas, xNote, note.getY() * heightRatio, columnPaints[colId]);
        }
    }
}
