package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;



public class GameView extends View {
    private GameDriver gameDriver;
    private Timer timer;
    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start() {
//        gameDriver.start()
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameDriver.getGameIsOver()) { // should be the condition that the game is over;
                    stop();
                } else {
//                    gameDriver.update();
                    invalidate();
                }
            }
        }, 0, 30);
    }

    public void stop() {
//        gameDriver.stop()
        timer.cancel();
        timer.purge();
    }
//
//    public void init(DisplayMetrics metrics) {
//        gameDriver.init(metrics);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        gameDriver.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                gameDriver.touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                gameDriver.touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP :
                gameDriver.touchUp();
                break;
        }

        return true;
    }

    public void setDriver(GameDriver driver) {
        this.gameDriver = driver;
    }
}







