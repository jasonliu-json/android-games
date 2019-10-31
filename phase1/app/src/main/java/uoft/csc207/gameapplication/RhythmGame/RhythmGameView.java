package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import uoft.csc207.gameapplication.MainActivity;
import uoft.csc207.gameapplication.MazeGame.MazeGameDriver;

public class RhythmGameView extends View {
    private RhythmGameDriver rhythmGameDriver;
    private Timer timer;
    public Context context;


    public RhythmGameView(Context context) {
        this(context, null);
    }

    public RhythmGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rhythmGameDriver = new RhythmGameDriver(context);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                rhythmGameDriver.update();
                invalidate();

                if (rhythmGameDriver.getGameIsOver()) { // should be the condition that the game is over;
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, 60);
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
