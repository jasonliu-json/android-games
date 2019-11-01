package uoft.csc207.gameapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;



public class GameView extends View {
    private GameWrapperDriver gameWrapperDriver;
    private Timer timer;
    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.gameWrapperDriver = new GameWrapperDriver(context);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                invalidate();
                if (gameWrapperDriver.getGameIsOver()) { // should be the condition that the game is over;
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, 30);
    }

    public void init(DisplayMetrics metrics) {
        gameWrapperDriver.init(metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        gameWrapperDriver.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                gameWrapperDriver.touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                gameWrapperDriver.touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                gameWrapperDriver.touchUp();
                invalidate();
                break;
        }

        return true;
    }

    public GameWrapperDriver getGameWrapperDriver() {
        return gameWrapperDriver;
    }
}







