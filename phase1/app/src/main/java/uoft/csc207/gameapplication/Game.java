package uoft.csc207.gameapplication;

import android.graphics.Canvas;

public abstract class Game {

    // Statistics
    private int pointsGained = 0;
    private int numDeaths = 0;
    //TODO: Add third statistic

    // Dimensions
    private int screenHeight, screenWidth;

    /**
     * Constructs a game with the specified screen height and width
     *
     * @param screenHeight height of the screen
     * @param screenWidth  width of the screen
     */
    public Game(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }


    /**
     * Updates the state of the game for the next frame
     */
    public abstract void update();

    /**
     * Draws a frame of the game
     *
     * @param canvas the graphics context to draw on
     */
    public abstract void draw(Canvas canvas);

    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }

    public void addPointsGained(int dPoints) {
        this.pointsGained += dPoints;
    }

    public int getNumDeaths() {
        return numDeaths;
    }

    public void setNumDeaths(int numDeaths) {
        this.numDeaths = numDeaths;
    }

    public void addNumDeaths(int dDeaths) {
        this.numDeaths += dDeaths;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
