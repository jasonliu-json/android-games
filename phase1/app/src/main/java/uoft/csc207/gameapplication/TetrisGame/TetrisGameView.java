package uoft.csc207.gameapplication.TetrisGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class TetrisGameView extends View {
    private TetrisGameDriver tetrisGameDriver;

    public TetrisGameView(Context context) {
        this(context, null);
    }

    public TetrisGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.tetrisGameDriver = new TetrisGameDriver();
    }

    public void init(DisplayMetrics metrics) {
        tetrisGameDriver.init(metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        tetrisGameDriver.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tetrisGameDriver.touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                tetrisGameDriver.touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                tetrisGameDriver.touchUp();
                invalidate();
                break;
        }

        return true;
    }
}
