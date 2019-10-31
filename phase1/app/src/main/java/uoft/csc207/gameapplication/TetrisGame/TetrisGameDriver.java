package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import uoft.csc207.gameapplication.GameDriver;

import static java.lang.Thread.sleep;

public class TetrisGameDriver extends GameDriver {

    private int X;
    private int Y;
    private TetrisGame tetrisGame;

    public TetrisGameDriver() {
        tetrisGame = new TetrisGame();
    }

    public void touchStart(float x, float y) {
        X = (int) x;
        Y = (int) y;
    }

    public void touchMove(float x, float y) {
        try {
            sleep(200);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (tetrisGame.getIsRunning()) {
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
                else if (yDistance < -20) {
                    tetrisGame.rotateFallingPieceClockwise();   // this should be temporary
                }
                X = (int)x;
                Y = (int)y;
            }
        }
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);

        drawGrid(newCanvas, tetrisGame.getBoard());
        drawBoard(newCanvas, tetrisGame.getBoard());

        canvas.drawBitmap(bitmap, 88, 88, null);

        Paint textPaint = new Paint();
        textPaint.setTextSize(100);
        newCanvas.drawText(String.valueOf(tetrisGame.getScore()), 10, 80, textPaint);

        newCanvas.restore();
    }

    private void drawGrid(Canvas canvas, Board board) {
        float length = canvas.getWidth() / (board.getWidth() + 2);   // side length of one tile
        float x = 0, y = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(3);
        for (int i = 0; i < 11; i++) {
            newCanvas.drawLine(x, 0, x, length * 20, paint);
            x = x + length;
        }
        for (int j = 0; j < 21; j++) {
            newCanvas.drawLine(0, y, length * 10, y, paint);
            y = y + length;
        }
    }

    private void drawBoard(Canvas canvas, Board board) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        int width = (canvas.getWidth() / 12);
        int height = (canvas.getWidth() / 12);
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++) {
                char block = board.getBoard()[i][k];
                int x = k * width;
                int y = i * height;
                if (block != '.') {
                    switch (block) {
                        case 'I':
                            paint.setColor(Color.rgb(130, 215,255));
                            break;
                        case 'J':
                            paint.setColor(Color.rgb(100, 170,255));
                            break;
                        case 'L':
                            paint.setColor(Color.rgb(255, 170,70));
                            break;
                        case 'O':
                            paint.setColor(Color.rgb(255, 220,100));
                            break;
                        case 'S':
                            paint.setColor(Color.rgb(155, 255,110));
                            break;
                        case 'Z':
                            paint.setColor(Color.rgb(255, 100,100));
                            break;
                        case 'T':
                            paint.setColor(Color.rgb(170, 140,255));
                            break;
                        default:
                            paint.setColor(Color.rgb(255, 255,255));
                    }
                    Rect rect = new Rect(x, y, x + width, y + width);
                    canvas.drawRect(rect, paint);
                }
            }
        }
    }
}




