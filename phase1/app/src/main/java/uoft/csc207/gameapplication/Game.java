package uoft.csc207.gameapplication;

import android.graphics.Canvas;

public abstract class Game {

    // Statistics
    private int pointsGained = 0;
    private int numDeaths = 0;

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
}
