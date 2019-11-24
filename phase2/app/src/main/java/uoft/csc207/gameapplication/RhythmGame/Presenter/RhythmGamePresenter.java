package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.Note;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameMessage;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.Target;

/**
 * How the game is represented on screen.
 */
public class RhythmGamePresenter {

    private RhythmGame rhythmGame;
    private int numColumns;

    private Bitmap bitmap;
    private Canvas bitCanvas;

    private int screenWidth, screenHeight;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;
    private Paint messagePaint;

    /* Scaling and Sizing variables */
    private float colSize;
    private float targetScale, noteScale;
    private float heightRatio;
    private int bitmapTop;

    public static final Map<Character, Integer> TETRO_COLOURS = new HashMap<>();

    public static final Map<Character, Integer[][]> TETRO_COORDINATES = new HashMap<>();
    static {
        Integer[][] jCoordinates = {{1, 0}, {1, 1}, {1, 2}, {0, 2}};
        TETRO_COORDINATES.put('J', jCoordinates);
        Integer[][] lCoordinates = {{0, 0}, {0, 1}, {0, 2}, {1, 2}};
        TETRO_COORDINATES.put('L', lCoordinates);
        Integer[][] oCoordinates = {{0, 0}, {1, 0}, {1, 1}, {0, 1}};
        TETRO_COORDINATES.put('O', oCoordinates);
        Integer[][] sCoordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 2}};
        TETRO_COORDINATES.put('S', sCoordinates);
        Integer[][] zCoordinates = {{1, 0}, {1, 1}, {0, 1}, {0, 2}};
        TETRO_COORDINATES.put('Z', zCoordinates);
        Integer[][] tCoordinates = {{0, 0}, {0, 1}, {0, 2}, {1, 1}};
        TETRO_COORDINATES.put('T', tCoordinates);

        TETRO_COLOURS.put('I', Color.rgb(130, 215,255));
        TETRO_COLOURS.put('J', Color.rgb(100, 170,255));
        TETRO_COLOURS.put('L', Color.rgb(255, 170,70));
        TETRO_COLOURS.put('O', Color.rgb(255, 220,100));
        TETRO_COLOURS.put('S', Color.rgb(155, 255,110));
        TETRO_COLOURS.put( 'Z', Color.rgb(255, 100,100));
        TETRO_COLOURS.put( 'T', Color.rgb(170, 140,255));
    }


    public RhythmGamePresenter(RhythmGame rhythmGame) {
        this.rhythmGame = rhythmGame;
        this.numColumns = rhythmGame.getNumColumns();
    }

    public void init(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        colSize = screenWidth / (float) numColumns;
        float colWidthRatio = colSize / 2; // Since each piece is 2 units wide

        // target width is 70% the width of the column
        targetScale = (float) 0.7 * colWidthRatio;      // the new size of one unit length for target

        // note width is 60% the width of the column
        noteScale = (float) 0.6 * colWidthRatio;    // the new size of one unit length for note

        bitmapTop = -3 * (int) Math.ceil(noteScale);

        heightRatio = (screenHeight - bitmapTop) / rhythmGame.getGameHeight();


        // add 3 times note scale, so when the note reaches 0,
        // the graphic representation of the note is completely offscreen
        bitmap = Bitmap.createBitmap(screenWidth,
                screenHeight - bitmapTop, Bitmap.Config.ARGB_8888);
        bitCanvas = new Canvas(bitmap);

        setTheme();
    }

    /* Sets up the paints and the shapes of the notes.
     */
    void setTheme() {
        char[] shapes = {'S', 'O', 'O', 'Z'};
//        try {
//            JSONFileRW fileRW = new JSONFileRW("Customize.json", rhythmGame.getContext());
//            JSONObject configs = fileRW.load();
//            JSONObject rhythmConfigs = configs.getJSONObject("rhythm");
//            for (int i = 0; i < numColumns; i++) {
//                shapes[i] = rhythmConfigs.getString(String.format(Locale.CANADA,
//                        "shape%d", i+1)).charAt(0);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        columnPaints = new Paint[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columnPaints[i] = new Paint();
            Integer colour = TETRO_COLOURS.get(shapes[i]);
            if (colour != null)
                columnPaints[i].setColor(colour);
            else
                columnPaints[i].setColor(Color.BLUE);
        }

        targetPaint = new Paint();
        targetPaint.setColor(Color.GRAY);

        colUnitNoteShapes = new NoteShape[numColumns];
        for (int i = 0; i < numColumns; i++) {
            Tetromino tetromino;
            Integer[][] coordinates = TETRO_COORDINATES.get(shapes[i]);
            if (coordinates != null)
                tetromino = new Tetromino(coordinates);
            else
                tetromino = new Tetromino(TETRO_COORDINATES.get('O'));

            TetrominoShape tetrominoShape = new TetrominoShape(tetromino);
            colUnitNoteShapes[i] = new NoteShape(tetrominoShape);
        }

        messagePaint = new Paint();
        messagePaint.setTextSize(50);
        messagePaint.setColor(Color.GREEN);
    }

    public void  draw(Canvas canvas){
        // TEMPORARY
        rhythmGame.timeUpdate();
        updateBitmap(bitCanvas);

        canvas.drawBitmap(bitmap, 0, bitmapTop, null);
        bitCanvas.restore();
    }

    protected void updateBitmap(Canvas bitCanvas) {
        bitCanvas.save();
        bitCanvas.drawColor(Color.WHITE);

        Target[] targets = rhythmGame.getAllTargets();
        SparseArray<List<Note>> notesMap = rhythmGame.getAllNotes();
        RhythmGameMessage[] messages = rhythmGame.getMessages();

        // draws the target, the notes, and the messages in each column
        for (int i = 0; i < numColumns; i++) {
            NoteShape scalableCopy = colUnitNoteShapes[i].clone();

            drawTarget(bitCanvas, targets[i], scalableCopy, i);

            List<Note> notes = notesMap.get(i);
            drawNotes(bitCanvas, notes, scalableCopy, i);

            bitCanvas.drawText(messages[i].getMessage(), i * colSize, 20 * heightRatio, messagePaint);
        }
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

    public RhythmGame getRhythmGame() {
        return rhythmGame;
    }

    public Canvas getBitCanvas() {
        return bitCanvas;
    }

    public int getBitmapTop() {
        return bitmapTop;
    }

    public void setBitmapTop(int bitmapTop) {
        this.bitmapTop = bitmapTop;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
