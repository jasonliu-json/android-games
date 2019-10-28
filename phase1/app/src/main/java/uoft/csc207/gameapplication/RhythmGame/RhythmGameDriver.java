package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import uoft.csc207.gameapplication.GameDriver;

public class RhythmGameDriver extends GameDriver {
    // private Paint circlePaint = new Paint();
    private Paint[] columnPaints;
    private Paint targetPaint = new Paint();
    private NoteShape[] colUnitNoteShapes;

    private int X;
    private int Y;

    private float startTime;
    private float elapsedTime;

    private static float stats[] = new float[5];
    // stats: score, timer, level/difficulty etc.

    private int numColumns = 4;

    private RhythmGame rhythmGame;

    public RhythmGameDriver() {
        //rhythmGame = new RhythmGame(screenHeight, screenWidth, 4);
        rhythmGame = new RhythmGame(numColumns);
        columnPaints = new Paint[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columnPaints[i] = new Paint();
            columnPaints[i].setColor(Color.BLUE);
        }

        targetPaint.setColor(Color.GRAY);

        colUnitNoteShapes = new NoteShape[numColumns];
        for (int i = 0; i < numColumns; i++) {
            int[][] coords = {{0, 0}, {0, 1}, {0, 2}, {1, 2}};
            Tetromino tetromino = new Tetromino(coords);
            TetrominoShape tetrominoShape = new TetrominoShape(tetromino);
            colUnitNoteShapes[i] = new NoteShape(tetrominoShape);
            startTime = System.currentTimeMillis();
        }

//        circlePaint.setColor(Color.BLACK);
//        circlePaint.setStyle(Paint.Style.FILL);
//        circlePaint.setStrokeWidth(10);
    }

    public float getTime() {
        elapsedTime = elapsedTime + (System.currentTimeMillis() - startTime);
        return elapsedTime;
    }

    public void touchStart(float x, float y) {
        // Do something to detect Touch given x, y pos
        int colNumber = (int) (4 * x / screenWidth);
        rhythmGame.tap(colNumber);
//        System.out.println(screenWidth);
//        System.out.println(x);
//        System.out.println(colNumber);
//        X = (int) x;
//        Y = (int) y;
    }

    public void touchMove(float x, float y) {
        // Do something when you cursor is moving around to x, y pos
        //X = (int) x;
//        Y = (int) y;
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();

        // some sort of draw method here
        // rhythmGame.update();
        rhythmGame.draw(newCanvas, screenHeight, screenWidth, colUnitNoteShapes,
                columnPaints, targetPaint);
        //newCanvas.drawCircle(X, Y, 20, circlePaint);

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }


}