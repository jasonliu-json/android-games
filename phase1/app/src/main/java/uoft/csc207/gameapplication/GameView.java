package uoft.csc207.gameapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import uoft.csc207.gameapplication.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameDriver;

public class GameView extends View {
//    private MazeGameDriver mazeGameDriver;
    private RhythmGameDriver rhythmGameDriver;
//     private TetrisGameDriver tetrisGameDriver;
    
    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rhythmGameDriver = new RhythmGameDriver(context);
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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//
//        switch(event.getAction()) {
//            case MotionEvent.ACTION_DOWN :
//                mazeGameDriver.touchStart(x, y);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_MOVE :
//                mazeGameDriver.touchMove(x, y);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP :
//                mazeGameDriver.touchUp();
//                invalidate();
//                break;
//        }
//
//        return true;
//    }
}







