package uoft.csc207.gameapplication;

import android.graphics.Canvas;

public abstract class Game {

    // Statistics
    private int pointsGained = 0;
    private int numDeaths = 0;
    //TODO: Add third statistic

    // Dimensions
    private int screenHeight, screenWidth;

    public Game(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public Game() {

    }


    public abstract void update();

    public abstract void draw(Canvas canvas);


    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }

    public int getNumDeaths() {
        return numDeaths;
    }

    public void setNumDeaths(int numDeaths) {
        this.numDeaths = numDeaths;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }
}
