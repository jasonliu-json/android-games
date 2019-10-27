package uoft.csc207.gameapplication.MazeGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;

import java.util.ArrayList;

public class MazeGameDriver {
    private ArrayList<Path> pathList = new ArrayList<>();
    private float pastX, pastY;
    private Path path;
    private int backgroundColor = Color.WHITE;
    private Canvas newCanvas;
    private Paint paint;
    private Bitmap bitmap;
    private Paint bitmapPaint = new Paint();

    public MazeGameDriver() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
//        creates a new bitmap of the screen size for drawing on
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        newCanvas = new Canvas(bitmap);

    }

    public void touchStart(float x, float y) {
//        initializes a new path for your line
        path = new Path();
        pathList.add(path);
//        starts the path at the point x, y
        path.moveTo(x, y);
        pastX = x;
        pastY = y;
    }

    public void touchMove(float x, float y) {
//        draws a quadratic bezier curve
        path.quadTo(pastX, pastY, (x + pastX)/2, (y + pastY)/2);
        pastX = x;
        pastY = y;
    }

    public void touchUp() {
        path.lineTo(pastX, pastY);
//        clears the path list ie the segments
//        path.reset();
    }

    public void draw(Canvas canvas) {
//        canvas.save();
        newCanvas.drawColor(backgroundColor);

        for (Path path : pathList) {
            newCanvas.drawPath(path, paint);
        }

        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
//        canvas.restore();
    }
}
