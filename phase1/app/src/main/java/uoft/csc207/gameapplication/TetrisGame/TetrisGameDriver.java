package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.GameDriver;

import static java.lang.Thread.sleep;

public class TetrisGameDriver extends GameDriver {

    private Paint circlePaint = new Paint();

    private int X;
    private int Y;
    private TetrisGame tetrisGame;

    public TetrisGameDriver() {
        tetrisGame = new TetrisGame();
//        circlePaint.setColor(Color.BLACK);
//        circlePaint.setStyle(Paint.Style.FILL);
//        circlePaint.setStrokeWidth(10);
    }

    public void touchStart(float x, float y) {
        X = (int) x;
        Y = (int) y;
    }

    public void touchMove(float x, float y) {
        try {
            sleep(40);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            int xDistance = (int) x - X;
            int yDistance = (int) y - Y;
            if (Math.abs(xDistance) > Math.abs(yDistance)) {
                if (xDistance > 20) {
                    tetrisGame.moveFallingPieceRight();
                }
                else if (xDistance < -20) {
                    tetrisGame.moveFallingPieceLeft();
                }
            }
            else if (yDistance > 20) {
                    tetrisGame.moveFallingPieceDown();
            }
            X = (int)x;
            Y = (int)y;
        }
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {

        // Just a note, unless we're already doing this, clear the canvas before drawing again

        newCanvas.save();

        float width = newCanvas.getWidth() / 12;
        float height = newCanvas.getWidth() / 12;
        int i, j;
        float x, y;

        x = 0;
        y = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        newCanvas.drawColor(Color.WHITE);

        for (i = 1; i <= 11; i++) {
            newCanvas.drawLine(x, 0, x, width * 20, paint);
            x = x + width;
        }
        for (j = 1; j <= 21; j++) {
            newCanvas.drawLine(0, y, height * 10, y, paint);
            y = y + height;
        }

        tetrisGame.getBoard().drawBoard(newCanvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        newCanvas.restore();
    }
}




