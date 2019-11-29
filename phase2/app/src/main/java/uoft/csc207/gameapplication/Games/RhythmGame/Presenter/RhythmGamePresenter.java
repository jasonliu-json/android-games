package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoft.csc207.gameapplication.R;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Note;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.ColumnMessage;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Target;

/**
 * How the game is represented on screen.
 */
public class RhythmGamePresenter {
    private RhythmGameLevel level;
    private Context context;
    private int numColumns;

    private Bitmap bitmap;
    private Canvas bitCanvas;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;
    private Paint messagePaint;

    /* Scaling and Sizing variables */
    private int screenWidth, screenHeight;
    private float colSize;
    private float targetScale, noteScale;
    private float heightRatio;
    private int bitmapTop;

    private MediaPlayer mediaPlayer;

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

    public RhythmGamePresenter(RhythmGameLevel level, DisplayMetrics metrics,
                               Context context, char[] shapes) {
        this.level = level;
        this.context = context;
        this.numColumns = level.getNumColumns();

        // Initialize the size of the screen nd sizing variables
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels - 40;

        colSize = screenWidth / (float) numColumns;
        float colWidthRatio = colSize / 2; // Since each piece is 2 units wide

        // target width is 70% the width of the column
        targetScale = (float) 0.7 * colWidthRatio;      // the new size of one unit length for target

        // note width is 60% the width of the column
        noteScale = (float) 0.6 * colWidthRatio;    // the new size of one unit length for note

        bitmapTop = -3 * (int) Math.ceil(noteScale);

        initializeBitmap();
        setSong(level.getSong());
        setTheme(shapes);
    }

    void initializeBitmap() {
        heightRatio = (screenHeight - bitmapTop) / level.getGameHeight();
        bitmap = Bitmap.createBitmap(screenWidth,
                screenHeight - bitmapTop, Bitmap.Config.ARGB_8888);
        bitCanvas = new Canvas(bitmap);
    }

    /**
     * Set up the paints and shapes of the notes.
     * @param shapes character array, where length is as long as numColumns
     */
    void setTheme(char[] shapes) {
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

    /**
     * Starts the song.
     */
    public void start() {
        mediaPlayer.start();
//        mediaPlayer.stop();
    }

    /**
     * Stops the song.
     */
    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    /**
     * Draws the game.
     * @param canvas the canvas to draw on.
     */
    public void draw(Canvas canvas){
        updateBitmap(bitCanvas);

        canvas.drawBitmap(bitmap, 0, bitmapTop, null);
        bitCanvas.restore();
    }

    /**
     * Updates the bitmap to the display the current state of the game.
     * @param bitCanvas the canvas to draw on
     */
    void updateBitmap(Canvas bitCanvas) {
        bitCanvas.save();
        bitCanvas.drawColor(Color.WHITE);

        Target[] targets = level.getAllTargets();
        SparseArray<List<Note>> notesMap = level.getAllNotes();
        ColumnMessage[] messages = level.getMessages();

        // draws the target, the notes, and the messages in each column
        for (int i = 0; i < numColumns; i++) {
            NoteShape scalableCopy = colUnitNoteShapes[i].copy();

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

    private void setSong(String song) {
        if (song.equalsIgnoreCase("Old Town Road"))
            mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
        else if (song.equalsIgnoreCase("Mii Channel"))
            mediaPlayer = MediaPlayer.create(context, R.raw.mii_channel);
        else
            mediaPlayer = MediaPlayer.create(context, R.raw.old_town_road);
    }

//    private MediaPlayer createMediaPlayer(String song) {
//        switch (song) {
//            case "Old Town Road":
//                return MediaPlayer.create(context, R.raw.old_town_road);
//            case "Mii Channel":
//                return MediaPlayer.create(context, R.raw.mii_channel);
//            default:
//                return MediaPlayer.create(context, R.raw.old_town_road);
//        }
//    }

//    private ArrayList<Long> generateIntervalsArray(String song) {
//        switch (song) {
//            case "Old Town Road":
//                return noteIntervals.generateIntervalsArray("oldTownRoadIntervals.csv");
//            case "Mii Channel":
//                return noteIntervals.generateIntervalsArray("miiChannelIntervals.csv");
//            default:
//                return noteIntervals.generateIntervalsArray("oldTownRoadIntervals.csv");
//        }
//    }

//    private MediaPlayer createMediaPlayer(String song) {
//        switch (song) {
//            case "Old Town Road":
//                return MediaPlayer.create(context, R.raw.old_town_road);
//            case "Mii Channel":
//                return MediaPlayer.create(context, R.raw.mii_channel);
//            default:
//                return MediaPlayer.create(context, R.raw.old_town_road);
//        }
//    }

    RhythmGameLevel getLevel() {
        return level;
    }

    int getBitmapTop() {
        return bitmapTop;
    }

    void setBitmapTop(int bitmapTop) {
        this.bitmapTop = bitmapTop;
    }

    public void setHeightRatio(float heightRatio) {
        this.heightRatio = heightRatio;
    }

    int getScreenWidth() {
        return screenWidth;
    }
}
