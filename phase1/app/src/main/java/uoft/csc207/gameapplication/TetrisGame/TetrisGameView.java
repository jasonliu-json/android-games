package uoft.csc207.gameapplication.TetrisGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class TetrisGameView extends View {
    private TetrisGameDriver tetrisGameDriver;
    private Timer timer;

    public TetrisGameView(Context context) {
        this(context, null);
    }

    public TetrisGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.tetrisGameDriver = new TetrisGameDriver();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                invalidate();
                if (false) { // should be the condition that the game is over;
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, 30);
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
