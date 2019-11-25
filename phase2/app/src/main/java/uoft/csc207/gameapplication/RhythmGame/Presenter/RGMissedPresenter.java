package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;

public class RGMissedPresenter extends RhythmGamePresenter {

    private Paint missedTextPaint = new Paint();;

    public RGMissedPresenter(RhythmGame rhythmGame, Song song) {
        super(rhythmGame, song);
        System.out.println("RGMissed Presenter");
    }

    @Override
    public void setTheme() {
        super.setTheme();

        missedTextPaint.setTextSize(getScreenHeight()/21);
        missedTextPaint.setColor(Color.RED);
    }

    @Override
    public void updateBitmap(Canvas bitCanvas) {
        super.updateBitmap(bitCanvas);

        bitCanvas.drawText("Missed: " + getRhythmGame().getNumMissed(), getScreenWidth()/2,
                80-getBitmapTop(), missedTextPaint);
    }
}
