package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGameLevel;

public class RGMissedPresenter extends RhythmGamePresenter {

    private Paint missedTextPaint;

    public RGMissedPresenter(RhythmGameLevel rhythmGameLevel, DisplayMetrics metrics, Context context, char[] shapes) {
        super(rhythmGameLevel, metrics, context, shapes);
    }

    @Override
    public void setTheme(char[] shapes) {
        super.setTheme(shapes);

        missedTextPaint = new Paint();
        missedTextPaint.setTextSize(60);
        missedTextPaint.setColor(Color.RED);
    }

    @Override
    public void updateBitmap(Canvas bitCanvas) {
        super.updateBitmap(bitCanvas);

        bitCanvas.drawText("Missed: " + getLevel().getNumMissed(), getScreenWidth()/2,
                80-getBitmapTop(), missedTextPaint);
    }
}
