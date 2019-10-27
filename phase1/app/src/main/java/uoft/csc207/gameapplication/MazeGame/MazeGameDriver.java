package uoft.csc207.gameapplication.MazeGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MazeGameDriver {
    private ArrayList<Path> pathList = new ArrayList<>();
    private float pastX, pastY;
    private Path path;
    private int backgroundColor = Color.WHITE;
    private Canvas newCanvas;
    private Paint wallPaint;
    private Paint endPaint = new Paint();
    private Paint startPaint = new Paint();
    private Bitmap bitmap;
    private Paint bitmapPaint = new Paint();
    private int screenHeight;
    private int screenWidth;
    private MazeGame mazeGame;
    private int Xinit;
    private int Yinit;

    public MazeGameDriver() {
        mazeGame = new MazeGame();
//        System.out.println("test");
        wallPaint = new Paint();
//        wallPaint.setColor(Color.BLACK);
//        wallPaint.setStyle(Paint.Style.STROKE);
//        wallPaint.setStrokeWidth(10);
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

//        creates a new bitmap of the screen size for drawing on
        bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);

    }

    public void touchStart(float x, float y) {
//        initializes a new path for your line
//        path = new Path();
//        pathList.add(path);
//        starts the path at the point x, y
//        path.moveTo(x, y);
//        pastX = x;
//        pastY = y;
        Xinit = (int) x;
        Yinit = (int) y;
    }

    public void touchMove(float x, float y) {
//        draws a quadratic bezier curve
//        path.quadTo(pastX, pastY, (x + pastX)/2, (y + pastY)/2);
//        pastX = x;
//        pastY = y;
//        System.out.println(x);
//        System.out.println(y);
        try {
            sleep(100);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            int xDistance = (int) x - Xinit;
            int yDistance = (int) y - Yinit;
//            System.out.println(xDistance);
//            System.out.println(yDistance);
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
//        path.lineTo(pastX, pastY);
//        clears the path list ie the segments
//        path.reset();
    }

    public void draw(Canvas canvas) {
        newCanvas.save();

        newCanvas.drawColor(backgroundColor);
//        float scale = new Float(0.9);
        newCanvas.scale(1.01f, 1.01f);
        int blockWidth = screenWidth / mazeGame.maze.length;
        int blockHeight = screenHeight / mazeGame.maze[0].length;
        for (int x = 0; x < mazeGame.maze.length; x++) {
            for (int y = 0; y < mazeGame.maze[0].length; y++) {
                if (mazeGame.maze[x][y].equals('W')) {
                    Rect r = new Rect(x * blockWidth, y*blockHeight,
                                (x + 1) * blockWidth, (y + 1)  * blockHeight);
                    newCanvas.drawRect(r, wallPaint);
                }
                else if (mazeGame.maze[x][y].equals('E')) {
                    Rect r = new Rect(x * blockWidth, y*blockHeight,
                            (x + 1) * blockWidth, (y + 1)  * blockHeight);
                    newCanvas.drawRect(r, endPaint);
                }
                else if (mazeGame.maze[x][y].equals('S')) {
                    Rect r = new Rect(x * blockWidth, y*blockHeight,
                            (x + 1) * blockWidth, (y + 1)  * blockHeight);
                    newCanvas.drawRect(r, startPaint);
                }
            }
        }
//        System.out.println(screenHeight);
//        System.out.println(screenWidth);
//        System.out.println(blockSize);
//        Rect r = new Rect(20 * blockSize, 20*blockSize, 21 * blockSize, 21 * blockSize);
//        newCanvas.drawRect(r, wallPaint);
//        for (Path p : pathList) {
//            newCanvas.drawPath(p, wallPaint);
//        }
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        newCanvas.restore();
    }
}
