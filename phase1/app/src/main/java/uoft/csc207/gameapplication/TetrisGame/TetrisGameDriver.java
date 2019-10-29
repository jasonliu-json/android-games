package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.GameDriver;

public class TetrisGameDriver extends GameDriver {
    private Paint circlePaint = new Paint();

    private int X;
    private int Y;
    private Piece piece;
    private  BoardV2 board;

    public TetrisGameDriver() {
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);
    }

    public void touchStart(float x, float y) {
        X = (int) x;
        Y = (int) y;
    }

    public void touchMove(float x, float y) {
        int xDistance = (int) x - X;
        int yDistance = (int) y - Y;
        if (Math.abs(xDistance) > Math.abs(yDistance)) {
            if (xDistance > 0) {
                piece.moveRight(board);
            }
            else {
                piece.moveLeft(board);
            }
        }
        else {
            if (yDistance > 0) {
                piece.moveDown(board);
            }
        }
    }

    public void touchUp() {
        // nothing required here for screen movement
    }

    public void draw(Canvas canvas) {
        newCanvas.save();

        board.drawBoard(canvas);
        piece.drawPiece(canvas, bitmap);

        newCanvas.restore();


    }

    public BoardV2 getBoard() {
        return board;
    }

    public Piece getPiece() {
        return piece;
    }
}


