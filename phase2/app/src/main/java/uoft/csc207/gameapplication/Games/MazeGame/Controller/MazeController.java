package uoft.csc207.gameapplication.Games.MazeGame.Controller;

public abstract class MazeController {
    public int touchStart(float x, float y) {return -1;}
    public int touchMove(float x, float y) {return -1;}
    public int touchUp() {return -1;}
}
