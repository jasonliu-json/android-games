package uoft.csc207.gameapplication;

/**
 *
 */
public abstract class GameController {
    private int screenHeight;
    private int screenWidth;

    public void init(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    /**
     * When the touch first encounters the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public abstract void touchStart(float x, float y);

    /**
     * As the touch moves around still in contact with the screen.
     * @param x the x-coordinate of the event on the screen.
     * @param y the y-coordinate of the event on the screen.
     */
    public abstract void touchMove(float x, float y);

    /**
     * When the touch is lifted off the screen.
     */
    public abstract void touchUp();

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }


}
