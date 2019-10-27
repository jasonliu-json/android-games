package uoft.csc207.gameapplication.MazeGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;


import static java.lang.Thread.sleep;

public class MazeGameDriver {
    private Bitmap bitmap;

    private Canvas newCanvas;

    private Paint wallPaint = new Paint();
    private Paint endPaint = new Paint();
    private Paint startPaint = new Paint();

    private int screenHeight;
    private int screenWidth;

    private MazeGame mazeGame;

    private int xInit;
    private int yInit;

    public MazeGameDriver(Context context) {
        mazeGame = new MazeGame(context);
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

    public void init(DisplayMetrics metrics) {
        screenHeight = metrics.heightPixels - 40;
        screenWidth = metrics.widthPixels;
        bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);
    }

    public void touchStart(float x, float y) {
        xInit = (int) x;
        yInit = (int) y;
    }

    public void touchMove(float x, float y) {
        try {
            sleep(100);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            int xDistance = (int) x - xInit;
            int yDistance = (int) y - yInit;
            if (Math.abs(xDistance) > Math.abs(yDistance)) {
                if (xDistance > 0) {
                    mazeGame.moveRight();
                }
                else {
                    mazeGame.moveLeft();
                }
            }
            else {
                if (yDistance > 0) {
                    mazeGame.moveDown();
                }
                else {
                    mazeGame.moveUp();
                }
            }
        }
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);
        newCanvas.scale(1.01f, 1.01f);

        int blockWidth = screenWidth / mazeGame.maze.length;
        int blockHeight = screenHeight / mazeGame.maze[0].length;

        for (int x = 0; x < mazeGame.maze.length; x++) {
            for (int y = 0; y < mazeGame.maze[0].length; y++) {
                Rect rect = new Rect(x * blockWidth, y*blockHeight,
                        (x + 1) * blockWidth, (y + 1)  * blockHeight);
                if (mazeGame.maze[x][y].equals('W')) {
                    newCanvas.drawRect(rect, wallPaint);
                }
                else if (mazeGame.maze[x][y].equals('E')) {
                    newCanvas.drawRect(rect, endPaint);
                }
                else if (mazeGame.maze[x][y].equals('S')) {
                    newCanvas.drawRect(rect, startPaint);
                }
            }
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }
}
