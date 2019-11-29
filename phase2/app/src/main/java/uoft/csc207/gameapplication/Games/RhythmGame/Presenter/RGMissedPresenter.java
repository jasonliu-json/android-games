package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;

public class RGMissedPresenter extends RhythmGamePresenter {
    private Paint missedTextPaint;

    public RGMissedPresenter(RhythmGameLevel rhythmGameLevel, DisplayMetrics metrics, Context context, char[] shapes) {
        super(rhythmGameLevel, metrics, context, shapes);
    }

    /**
     * Sets up paints.
     * @param shapes character array, where length is as long as numColumns
     */
    @Override
    public void setTheme(char[] shapes) {
        super.setTheme(shapes);

        missedTextPaint = new Paint();
        missedTextPaint.setTextSize(60);
        missedTextPaint.setColor(Color.RED);
    }

    /**
     * Draws the game and displays number of notes missed.
     * @param bitCanvas the canvas to draw on
     */
    @Override
    public void updateBitmap(Canvas bitCanvas) {
        super.updateBitmap(bitCanvas);

        bitCanvas.drawText("Missed: " + getLevel().getNumMissed(), getScreenWidth()/2,
                80 - getBitmapTop(), missedTextPaint);
    }
}
