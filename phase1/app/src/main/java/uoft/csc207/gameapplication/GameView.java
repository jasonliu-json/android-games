package uoft.csc207.gameapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The game view. Taken from FishTank Assignment.
 */
public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

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
     * Create a new game in the context environment.
     *
     * @param context the environment.
     */
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setClickable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        game = initializeGame(screenHeight, screenWidth);

        thread.setRunning(true);
        thread.start();
    }

    /**
     * Constructs a new Game object and initializes its event handlers.
     *
     * @param screenHeight height of the screen
     * @param screenWidth  width of the screen
     * @return the game
     */
    protected abstract Game initializeGame(int screenHeight, int screenWidth);

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
     * Update the game state for the next frame
     */
    public void update() {
        game.update();
    }

    /**
     * Draws the current state of the game
     * @param canvas the graphics context to draw the game on
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) game.draw(canvas);

    }


}







