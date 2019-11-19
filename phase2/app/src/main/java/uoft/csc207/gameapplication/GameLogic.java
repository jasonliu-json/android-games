package uoft.csc207.gameapplication;

public abstract class GameLogic {
    public abstract void start();

    public abstract void stop();

    public abstract void timeUpdate();

    public abstract int getPoints();

    public abstract boolean getIsGameOver();
}
