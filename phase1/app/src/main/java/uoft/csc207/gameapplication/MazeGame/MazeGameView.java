package uoft.csc207.gameapplication.MazeGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


public class MazeGameView extends View {
    private MazeGameDriver mazeGameDriver;

    public MazeGameView(Context context) {
        this(context, null);
    }

    public MazeGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mazeGameDriver = new MazeGameDriver(context);
    }

    public void init(DisplayMetrics metrics, Intent test) {
        mazeGameDriver.init(metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mazeGameDriver.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                mazeGameDriver.touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                mazeGameDriver.touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                mazeGameDriver.touchUp();
                invalidate();
                break;
        }

        return true;
    }
}
