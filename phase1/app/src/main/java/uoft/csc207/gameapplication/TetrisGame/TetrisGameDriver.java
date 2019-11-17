package uoft.csc207.gameapplication.TetrisGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import uoft.csc207.gameapplication.GameDriver;

import static java.lang.Thread.sleep;

public class TetrisGameDriver extends GameDriver {

    private int X;
    private int Y;
    private int Xinit;
    private int Yinit;
    private TetrisGame tetrisGame;

    public TetrisGameDriver(Context context) {
        Board board = new Board(10, 20);
        Randomizer randomizer = new Randomizer();
        tetrisGame = new TetrisGame(board, randomizer);
    }

    public void touchStart(float x, float y) {
        X = (int) x;
        Y = (int) y;
        Xinit = (int) x;
        Yinit = (int) y;
    }

    public void touchMove(float x, float y) {
        try {
            sleep(40);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (!tetrisGame.getGameIsOver()) {
                int xDistance = (int) x - X;
                int yDistance = (int) y - Y;
                if (xDistance > 20) {
                    tetrisGame.moveRight();
                }
                else if (xDistance < -20) {
                    tetrisGame.moveLeft();
                }
                else if (yDistance > 20) {
                    tetrisGame.moveDown();
                }
                X = (int)x;
                Y = (int)y;
            }
        }
    }

    public void touchUp() {
        // nothing required here for screen movement
        if (Math.abs(X - Xinit) < 10 && Math.abs(Y - Yinit) < 10) {
            tetrisGame.rotateClockwise();
        }
    }

    public void draw(Canvas canvas) {
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);

        drawGrid(newCanvas, tetrisGame.getBoard());
        drawBoard(newCanvas, tetrisGame.getBoard());

        canvas.drawBitmap(bitmap, 88, 88, null);

//        // draw points
//        Paint textPaint = new Paint();
//        textPaint.setTextSize(100);
//        newCanvas.drawText(String.valueOf(tetrisGame.getPoints()), 10, 80, textPaint);

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

    public boolean getGameIsOver() {
        return tetrisGame.getGameIsOver();
    }

    public int getPoints() {
        return tetrisGame.getPoints();
    }

    private void drawBoard(Canvas canvas, Board board) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        int width = (canvas.getWidth() / 12);
        int height = (canvas.getWidth() / 12);
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++) {
                char block = board.getGrid()[i][k];
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




