package uoft.csc207.gameapplication.Games.MazeGame.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MazePresenter {
    private Paint wallPaint = new Paint();
    private Paint endPaint = new Paint();
    private Paint startPaint = new Paint();


    public MazePresenter() {
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStyle(Paint.Style.FILL);
        wallPaint.setStrokeWidth(1);
        endPaint.setColor(Color.RED);
        endPaint.setStyle(Paint.Style.FILL);
        endPaint.setStrokeWidth(1);
        startPaint.setColor(Color.GREEN);
        startPaint.setStyle(Paint.Style.FILL);
        startPaint.setStrokeWidth(1);
    }

    /**
     * draws the maze game
     *
     * @param canvas the graphics canvas where we draw on
     * @param screenWidth the screen width
     * @param screenHeight the screen height
     */
    public void draw(Canvas canvas, Character[][] maze, int screenWidth, int screenHeight) {
        int blockWidth = screenWidth / maze.length;
        int blockHeight = screenHeight / maze[0].length;

        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                Rect rect = new Rect(x * blockWidth, y * blockHeight,
                        (x + 1) * blockWidth, (y + 1) * blockHeight);
                if (maze[x][y].equals('W')) {
                    canvas.drawRect(rect, wallPaint);
                } else if (maze[x][y].equals('E')) {
                    canvas.drawRect(rect, endPaint);
                } else if (maze[x][y].equals('S')) {
                    canvas.drawRect(rect, startPaint);
                }
            }
        }
    }
}
