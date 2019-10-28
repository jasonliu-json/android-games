package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import android.util.Pair;



/* A game where notes ascend the screen and the player aims to tap the
 * note precisely when the note overlaps a fixed note shadow. */
public class RhythmGame {
    private int numColumns;
    private ArrayList<Column> columns;
    private float colSize;
    private int screenHeight;


    /**
     * Constructs the Rhythm game
     *
     * @param screenHeight height of the screen
     * @param screenWidth  width of the screen
     * @param numColumns   number of columns of the game
     */
    public RhythmGame(int screenHeight, int screenWidth, int numColumns) {
        this.screenHeight = screenHeight;
        this.numColumns = numColumns;
        this.colSize = screenWidth / (float) numColumns;

        createColumns();
//        setPointsGained(0);
//        setNumDeaths(0);
    }

    /**
     * Initializes the columns of the game
     */
    public void createColumns() {
        // get the theme of each column (the shape of the notes and the colour)
        ArrayList<Pair<NoteShape, Paint>> colThemes = getTheme();
        columns = new ArrayList<>(numColumns);

        for (int i = 0; i < numColumns; i++) {
            Pair<NoteShape, Paint> theme = colThemes.get(i);
            columns.add(new Column((int) (colSize * i), (int) colSize, screenHeight, theme.first, theme.second));
        }
    }

    /**
     * Updates the state of the game for the next frame
     */

    public void update() {
        for (Column col : columns) col.update();

        // update time
    }

    /**
     * Draws a frame of the game in the current state
     *
     * @param canvas the graphics context to draw on
     */

    public void draw(Canvas canvas) {
        //columns.get(0).draw(canvas);

        for (Column col : columns) col.draw(canvas);

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.YELLOW);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);
        canvas.drawCircle(colSize * 2, screenHeight / 2, 20, circlePaint);

        // draw statistics
        // draw time
    }

    /**
     * @return a list of a pair of the note shape and colour for each column
     */
    private ArrayList<Pair<NoteShape, Paint>> getTheme() {
        // later implementation will allow for customizability
        ArrayList<Pair<NoteShape, Paint>> lst = new ArrayList<>(numColumns);
        for (int i = 0; i < numColumns; i++) {
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            int[][] coords = new int[][]{{1, 0}, {1, 1}, {0, 1}, {2, 1}};
            lst.add(new Pair<>(new NoteShape(new TetrominoShape(new Tetromino(coords))), paint));
        }

        return lst;
    }

    public void tap(float xPos) {
        for (int i = 0; i < numColumns; i++) {
            if (xPos < i * colSize) {
                columns.get(i).tap();
                break;
            }
        }
    }
}
