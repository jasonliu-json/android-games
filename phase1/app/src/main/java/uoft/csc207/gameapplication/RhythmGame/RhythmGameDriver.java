package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;
import android.os.Bundle;

import uoft.csc207.gameapplication.GameDriver;
import uoft.csc207.gameapplication.MainActivity;

public class RhythmGameDriver extends GameDriver {
    // private Paint circlePaint = new Paint();
    private Paint[] columnPaints;
    private Paint targetPaint = new Paint();
    private NoteShape[] colUnitNoteShapes;

    private int X;
    private int Y;


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

        }
//        circlePaint.setColor(Color.BLACK);
//        circlePaint.setStyle(Paint.Style.FILL);
//        circlePaint.setStrokeWidth(10);
    }

    public void update() {
        rhythmGame.update();
    }

    public static void popUpMessage() {

//    Toast.makeText(,"Hello Javatpoint",Toast.LENGTH_SHORT).show();
    }
//
//    public static void popUpMessage() {
//        Context context =
//        CharSequence text = "Hello toast!";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//    }


//    public float getTime() {
//        elapsedTime = elapsedTime + (System.currentTimeMillis() - startTime);
//        return elapsedTime;
//    }

    public void touchStart(float x, float y) {

        // note: touchStart brings next frame; still need to implement main game thread

        // Do something to detect Touch given x, y pos
        int colNumber = (int) (4 * x / screenWidth);
        rhythmGame.tap(colNumber);
        //rhythmGame.update();
//        rhythmGame.update();


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
       // rhythmGame.update();
    }


    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();
//        rhythmGame.update();
        Paint tempPaint = new Paint();
        tempPaint.setColor(Color.WHITE);
        newCanvas.drawRect(0,0, screenWidth, screenHeight, tempPaint);
        // some sort of draw method here
        // rhythmGame.update();
        rhythmGame.draw(newCanvas, screenHeight, screenWidth, colUnitNoteShapes,
                columnPaints, targetPaint);
        //newCanvas.drawCircle(X, Y, 20, circlePaint);
        String s = "Score:  ";
        System.out.println(s + RhythmGame.getScore());

        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }


}
