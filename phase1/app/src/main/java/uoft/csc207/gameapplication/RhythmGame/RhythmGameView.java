package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import uoft.csc207.gameapplication.MazeGame.MazeGameDriver;

public class RhythmGameView extends View {
    private RhythmGameDriver rhythmGameDriver;
    public RhythmGameView(Context context) {
        this(context, null);
    }

    public RhythmGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rhythmGameDriver = new RhythmGameDriver();
    }

    public void init(DisplayMetrics metrics) {
        rhythmGameDriver.init(metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rhythmGameDriver.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                rhythmGameDriver.touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                rhythmGameDriver.touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                rhythmGameDriver.touchUp();
                invalidate();
                break;
        }

        return true;
    }
}
