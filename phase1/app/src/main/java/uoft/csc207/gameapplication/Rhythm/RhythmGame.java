package uoft.csc207.gameapplication.Rhythm;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import uoft.csc207.gameapplication.Game;

/* A game where notes ascend the screen and the player aims to tap the note precisely when the note
 * overlaps a fixed note shadow. */
public class RhythmGame extends Game {
    private static int numColumns = 4;
    private ArrayList<Column> columns;

    // Create an anonymous implementation of OnTouchListener
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        /**
         * Determines which column was tapped and taps the column.
         * @param v the view where the even occurred
         * @param e the motion event
         * @return true if the event was consumed
         */
        public boolean onTouch(View v, MotionEvent e) {
            float xPos = e.getX();
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                for (int i = 0; i < numColumns; i++) {
                    if (xPos < getScreenWidth() * (i + 1) / (float) numColumns) {
                        columns.get(i).tap();
                        return true;
                    }
                }
            }

            return false;
        }
    };

    /**
     * Constructs the Rhythm game
     *
     * @param screenHeight height of the screen
     * @param screenWidth  width of the screen
     */
    public RhythmGame(int screenHeight, int screenWidth) {
        super(screenHeight, screenWidth);
        setPointsGained(0);
        setNumDeaths(0);
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
            columns.add(new Column(getScreenWidth() * (i + 1) / numColumns, theme.first, theme.second));
        }
    }

    /**
     * Updates the state of the game for the next frame
     */
    @Override
    public void update() {
        for (Column col : columns) col.update();


    }

    /**
     * Draws a frame of the game in the current state
     *
     * @param canvas the graphics context to draw on
     */
    @Override
    public void draw(Canvas canvas) {
        for (Column col : columns) col.draw(canvas);
    }

    /**
     *
     * @return a list of a pair of the note shape and colour for each column
     */
    private ArrayList<Pair<NoteShape, Paint>> getTheme() {
        // later implementation will allow for customizability
        ArrayList<Pair<NoteShape, Paint>> lst = new ArrayList<>(numColumns);
        for (int i = 0; i < numColumns; i++) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            lst.add(new Pair<>(new NoteShape(), paint));
        }

        return lst;
    }

    public View.OnTouchListener getTouchListener() {
        return touchListener;
    }
}
