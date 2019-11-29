package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGamePointsSystem;

public class RGStatsPresenter extends RhythmGamePresenter {
    private RhythmGamePointsSystem pointsSystem;

    private Paint goodStatsPaint;
    private Paint badStatsPaint;
    private Paint upperRectPaint = new Paint();
    private int textHeight;

    public RGStatsPresenter(RhythmGameLevel rhythmGameLevel, DisplayMetrics metrics,
                            Context context, char[] shapes) {
        super(rhythmGameLevel, metrics, context, shapes);
        pointsSystem = rhythmGameLevel.getPointsSystem();

        // Change the height of the bitmap
        Rect bounds = new Rect();
        goodStatsPaint.getTextBounds("Perfect: ", 0, "Perfect".length(), bounds);
        textHeight = bounds.height();
        setBitmapTop(getBitmapTop() + 4 * textHeight);
        initializeBitmap();
    }


    /**
     * Sets up paints.
     * @param shapes character array, where length is as long as numColumns
     */
    @Override
    void setTheme(char[] shapes) {
        super.setTheme(shapes);

        upperRectPaint = new Paint();
        upperRectPaint.setColor(Color.BLACK);

        goodStatsPaint = new Paint();
        goodStatsPaint.setTextSize(60);
        goodStatsPaint.setColor(Color.GREEN);

        badStatsPaint = new Paint();
        badStatsPaint.setTextSize(60);
        badStatsPaint.setColor(Color.RED);
    }

    /**
     * Draws the game and displays the statistics.
     * @param bitCanvas the canvas to draw on
     */
    @Override
    void updateBitmap(Canvas bitCanvas) {
        super.updateBitmap(bitCanvas);

        bitCanvas.drawRect(0, 0, getScreenWidth(), 4 * textHeight, upperRectPaint);

        int leftSpacing = 80;
        int upperSpacing = -getBitmapTop() + 70;
        bitCanvas.drawText("Perfect: " + pointsSystem.getNumPerfect(),
                leftSpacing, upperSpacing, goodStatsPaint);
        bitCanvas.drawText("Great: " + pointsSystem.getNumGreat(),
                leftSpacing, upperSpacing + textHeight + 30, goodStatsPaint);

        bitCanvas.drawText("Good: " + pointsSystem.getNumGood(),
                leftSpacing + getScreenWidth()/2, upperSpacing, goodStatsPaint);
        bitCanvas.drawText("Missed: " + pointsSystem.getNumMissed(),
                leftSpacing + getScreenWidth()/2, upperSpacing + textHeight + 30, badStatsPaint);
    }
}
