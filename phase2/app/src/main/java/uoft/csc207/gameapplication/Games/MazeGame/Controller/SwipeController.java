package uoft.csc207.gameapplication.Games.MazeGame.Controller;

public class SwipeController extends MazeController{
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    @Override
    public int touchStart(float x, float y) {
        startX = x;
        startY = y;
        endX = x;
        endY = y;
        return -1;
    }

    @Override
    public int touchMove(float x, float y) {
        endX = x;
        endY = y;
        return -1;
    }

    @Override
    public int touchUp() {
        int xDiff = (int)(endX - startX);
        int yDiff = (int)(endY - startY);
        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > 0) {
                return 2;
            }
            return 4;
        }
        else {
            if (yDiff > 0) {
                return 3;
            }
            return 1;
        }
    }

}
