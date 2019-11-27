package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameLevel;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGamePointsSystem;

public class RGStatsPresenter extends RhythmGamePresenter {

    private RhythmGamePointsSystem pointsSystem;

    private Paint goodStatsPaint = new Paint();
    private Paint badStatsPaint = new Paint();
    private Paint upperRectPaint = new Paint();
    private int textHeight;

    public RGStatsPresenter(RhythmGameLevel rhythmGameLevel, Context context, char[] shapes) {
        super(rhythmGameLevel, context, shapes);
        pointsSystem = rhythmGameLevel.getPointsSystem();
    }

    @Override
    public void init(int screenWidth, int screenHeight) {
        super.init(screenWidth, screenHeight);

        Rect bounds = new Rect();
        goodStatsPaint.getTextBounds("Perfect: ", 0, "Perfect".length(), bounds);
        textHeight = bounds.height();
        setBitmapTop(getBitmapTop()+ 4 * textHeight);
    }

    @Override
    void setTheme(char[] shapes) {
        super.setTheme(shapes);

        upperRectPaint.setColor(Color.BLACK);

        goodStatsPaint.setTextSize(60);
        goodStatsPaint.setColor(Color.GREEN);

        badStatsPaint.setTextSize(60);
        badStatsPaint.setColor(Color.RED);
    }

    @Override
    public void updateBitmap(Canvas bitCanvas) {
        super.updateBitmap(bitCanvas);

        bitCanvas.drawRect(0, 0, getScreenWidth(), -getBitmapTop() + 4* textHeight, upperRectPaint);

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
