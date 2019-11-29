package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGamePointsSystem;
import uoft.csc207.gameapplication.R;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Note;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.ColumnMessage;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Target;

/**
 * Presents the game to screen.
 */
public class RhythmGamePresenter {
    private RhythmGameLevel level;
    private RhythmGamePointsSystem pointsSystem;
    private Context context;
    private int numColumns;

    private Bitmap bitmap;
    private Canvas bitCanvas;

    /* Scaling and Sizing variables */
    private int screenWidth, screenHeight;
    private float colSize;
    private float targetScale, noteScale;
    private float heightRatio;
    private int bitmapTop;
    private int statsTextHeight;

    /* Graphic variables for drawing */
    private Paint[] columnPaints;
    private Paint targetPaint;
    private NoteShape[] colUnitNoteShapes;
    private char[] shapes;
    private String colourTheme;
    private Paint goodMessagePaint;
    private Paint badMessagePaint;
    private Paint badStatsPaint;
    private Paint goodStatsPaint;
    private Paint upperRectPaint;

    private String statsDisplayMode;
    private MediaPlayer mediaPlayer;

    public static final Map<Character, Integer> TETRO_COLOURS = new HashMap<>();

    public static final Map<Character, Integer[][]> TETRO_COORDINATES = new HashMap<>();

    public static final Map<String, Integer> SONG_IDS = new HashMap<>();
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

        SONG_IDS.put("Old Town Road", R.raw.old_town_road);
        SONG_IDS.put("Mii Channel", R.raw.mii_channel);
    }

    public RhythmGamePresenter(RhythmGameLevel level, DisplayMetrics metrics,
                               Context context, char[] shapes, String colourTheme, String statsDisplayMode) {
        this.context = context;
        this.shapes = shapes;
        this.colourTheme = colourTheme;
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels - 40;

        this.statsDisplayMode = statsDisplayMode;

        setLevel(level);
        setPaints();

        Rect bounds = new Rect();
        Paint sizePaint = new Paint();
        sizePaint.setTextSize(60);
        sizePaint.getTextBounds("Perfect: ", 0, "Perfect".length(), bounds);
        statsTextHeight = bounds.height();
    }

    public void setLevel(RhythmGameLevel level) {
        this.level = level;
        this.numColumns = level.getNumColumns();
        this.pointsSystem = level.getPointsSystem();

        setTheme();
        // Set the song
        Integer rawSongId = SONG_IDS.get(level.getSong());
        if (rawSongId == null) rawSongId = R.raw.mii_channel;
        mediaPlayer = MediaPlayer.create(context, rawSongId);

        initializeSizing();
    }

    private void setTheme() {
        columnPaints = new Paint[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columnPaints[i] = new Paint();
            Integer colour = TETRO_COLOURS.get(shapes[i]);
            if (colour != null) columnPaints[i].setColor(colour);
        }

        colUnitNoteShapes = new NoteShape[numColumns];
        for (int i = 0; i < numColumns; i++) {
            Integer[][] coordinates = TETRO_COORDINATES.get(shapes[i]);
            if (coordinates == null) coordinates = TETRO_COORDINATES.get('O');

            TetrominoShape tetrominoShape = new TetrominoShape(new Tetromino(coordinates));
            colUnitNoteShapes[i] = new NoteShape(tetrominoShape);
        }
    }

    private void setPaints() {
        targetPaint = new Paint();
        targetPaint.setColor(Color.GRAY);

        goodMessagePaint = new Paint();
        goodMessagePaint.setTextSize(50);
        goodMessagePaint.setColor(Color.GREEN);

        badMessagePaint = new Paint();
        badMessagePaint.setTextSize(50);
        badMessagePaint.setColor(Color.RED);

        upperRectPaint = new Paint();
        upperRectPaint.setColor(Color.BLACK);

        goodStatsPaint = new Paint();
        goodStatsPaint.setTextSize(60);
        goodStatsPaint.setColor(Color.GREEN);

        badStatsPaint = new Paint();
        badStatsPaint.setTextSize(60);
        badStatsPaint.setColor(Color.RED);
    }

    private void initializeSizing() {
        colSize = screenWidth / (float) numColumns;
        float colWidthRatio = colSize / 2; // Since each piece is 2 units wide

        // target width is 70% the width of the column
        targetScale = (float) 0.7 * colWidthRatio;      // the new size of one unit length for target

        // note width is 60% the width of the column
        noteScale = (float) 0.6 * colWidthRatio;    // the new size of one unit length for note

        bitmapTop = -3 * (int) Math.ceil(noteScale);

        heightRatio = (screenHeight - bitmapTop) / level.getGameHeight();
        bitmap = Bitmap.createBitmap(screenWidth,
                screenHeight - bitmapTop, Bitmap.Config.ARGB_8888);
        bitCanvas = new Canvas(bitmap);
    }

    /**
     * Starts the song.
     */
    public void start() {
        mediaPlayer.start();
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
        bitCanvas.save();
        bitCanvas.drawColor(Color.WHITE);

        Target[] targets = level.getAllTargets();
        SparseArray<List<Note>> notesMap = level.getAllNotes();
        ColumnMessage[] messages = level.getMessages();

        // draws the target, the notes, and the messages in each column
        for (int i = 0; i < numColumns; i++) {
            NoteShape scalableCopy = colUnitNoteShapes[i].copy();

            drawTarget(targets[i], scalableCopy, i);

            List<Note> notes = notesMap.get(i);
            drawNotes(notes, scalableCopy, i);

            drawMessage(messages[i].getMessage(), targets[i], i);
        }

        // Draw statistics
        if (statsDisplayMode.equalsIgnoreCase("MISSED")) {
            bitCanvas.drawText("Missed: " + pointsSystem.getNumMissed(), screenWidth/2,
                    80 - bitmapTop, badStatsPaint);
        } else {
            drawStats();
        }

        canvas.drawBitmap(bitmap, 0, bitmapTop, null);
        bitCanvas.restore();
    }

    private void drawTarget(Target target, NoteShape scalableShape, int colId) {
        scalableShape.setScale(targetScale);
        // centre the target in the column
        scalableShape.draw(bitCanvas, centreInColumn(colId, 2 * targetScale),
                target.getY() * heightRatio, targetPaint);
    }

    private void drawNotes(List<Note> notes, NoteShape scalableShape, int colId) {
        scalableShape.setScale(noteScale);
        // centre the note in the column
        for (Note note : notes) {
            scalableShape.draw(bitCanvas, centreInColumn(colId, 2 * noteScale),
                    note.getY() * heightRatio, columnPaints[colId]);
        }
    }

    private void drawMessage(String message, Target target, int colId) {
        if (message.length() != 0) {
            Paint messagePaint = goodMessagePaint;
            if (message.charAt(0) == 'B')
                messagePaint = badMessagePaint;

            Rect bounds = new Rect();
            goodStatsPaint.getTextBounds(message, 0, message.length(), bounds);
            int textWidth = bounds.width();

            // draws the message centre of the column, on below of the target
            bitCanvas.drawText(message, centreInColumn(colId, textWidth),
                    target.getY() * heightRatio + 3 * targetScale, messagePaint);
        }
    }

    /**
     * Returns the x-coordinate to be drawn, such that the drawing will be centred within the coloumn
     * @param colId the number of the column
     * @param shapeWidth the width of the shape being drawn
     * @return x-coordinate where the drawing will be centred in the column
     */
    private float centreInColumn(int colId, float shapeWidth) {
        return (float) ((colId + 0.5) * colSize - shapeWidth / 2);
    }

    private void drawStats() {
        bitCanvas.drawRect(0, 0, screenWidth, -bitmapTop + 4 * statsTextHeight, upperRectPaint);

        int leftSpacing = 80;
        int upperSpacing = -bitmapTop + 70;
        bitCanvas.drawText("Perfect: " + pointsSystem.getNumPerfect(),
                leftSpacing, upperSpacing, goodStatsPaint);
        bitCanvas.drawText("Great: " + pointsSystem.getNumGreat(),
                leftSpacing, upperSpacing + statsTextHeight + 30, goodStatsPaint);

        bitCanvas.drawText("Good: " + pointsSystem.getNumGood(),
                leftSpacing + screenWidth/2, upperSpacing, goodStatsPaint);
        bitCanvas.drawText("Missed: " + pointsSystem.getNumMissed(),
                leftSpacing + screenWidth/2, upperSpacing + statsTextHeight + 30, badStatsPaint);
    }
}
