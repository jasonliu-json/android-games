package uoft.csc207.gameapplication;

// THIS CLASS WILL NOT BE NEEDED KEEPING IT FOR INFO REFERENCE;

public abstract class SubGame {
    // Statistics
    private int points = 0;
    private int numDeaths = 0;
    //TODO: Add third statistic

    private boolean isGameOver = false;

    /**
     * Updates the state of the game for the next frame
     */
    public abstract void update();

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int dPoints) {
        this.points += dPoints;
    }
//
//    public int getNumDeaths() {
//        return numDeaths;
//    }
//
//    public void setNumDeaths(int numDeaths) {
//        this.numDeaths = numDeaths;
//    }
//
//    public void addNumDeaths(int dDeaths) {
//        this.numDeaths += dDeaths;
//    }

}