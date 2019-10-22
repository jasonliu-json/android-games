package uoft.csc207.gameapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The game view. Taken from FishTank Assignment.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Screen width.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The game.
     */
    public Game game;
    /**
     * The part of the program that manages time.
     */
    private MainThread thread;

    /**
     * Create a new fish tank in the context environment.
     *
     * @param context the environment.
     */
    public GameView(Context context, Game game) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setClickable(true);
        this.game = game;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        game.setScreenHeight(screenHeight);
        game.setScreenWidth(screenWidth);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Update the game state.
     */
    public void update() {
        game.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            game.draw(canvas);
        }

    }


}







