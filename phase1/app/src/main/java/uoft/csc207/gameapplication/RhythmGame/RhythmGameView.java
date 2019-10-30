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
    private RunWithTimer thread;
    private Timer timer;
    public Context context;


    public RhythmGameView(Context context) {
        this(context, null);
    }

    public RhythmGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rhythmGameDriver = new RhythmGameDriver(context);
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                update();
//                invalidate();
//
//                if (false) { // should be the condition that the game is over;
//                    timer.cancel();
//                    timer.purge();
//                }
//            }
//        }, 100, 20000);

        thread = new RunWithTimer(this);
    }

//    public static void displayMessage(String message) {
//        // something about Toast should go here, will implement later
//        displayText(message);
//
//    }
//
//    public static void displayText(String message) {
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//    }

    public void startThread() {
//        thread.setRunning(true);
        thread.start();
    }

    public void init(DisplayMetrics metrics) {
        rhythmGameDriver.init(metrics);
        startThread();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rhythmGameDriver.draw(canvas);
//        rhythmGameDriver.update();
    }

    public void update() {
        rhythmGameDriver.update();
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
