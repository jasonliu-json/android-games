package uoft.csc207.gameapplication.TetrisGame.Presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Map;

import uoft.csc207.gameapplication.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.TetrisGame.GameLogic.TetrisGame;


public class TetrisGamePresenter {

    private TetrisGame tetrisGame;
    private static Map<Character, Integer> colorScheme;

    public TetrisGamePresenter(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        colorScheme = new HashMap<>();
        colorScheme.put('I', Color.rgb(130, 215, 255));
        colorScheme.put('J', Color.rgb(100, 170, 255));
        colorScheme.put('L', Color.rgb(255, 170, 70));
        colorScheme.put('O', Color.rgb(255, 220, 100));
        colorScheme.put('S', Color.rgb(155, 255, 110));
        colorScheme.put('Z', Color.rgb(255, 100, 100));
        colorScheme.put('T', Color.rgb(170, 140, 255));
    }

    public void draw(Canvas canvas, Bitmap bitmap) {
        Canvas newCanvas = new Canvas(bitmap);
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);
        drawGrid(newCanvas, tetrisGame.getBoard());
        drawBlocks(newCanvas, tetrisGame.getBoard());
        canvas.drawBitmap(bitmap, 88, 88, null);
        newCanvas.restore();
    }

    private void drawGrid(Canvas canvas, Board board) {
        float length = canvas.getWidth() / (board.getWidth() + 2);
        float x = 0;
        float y = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(3);
        for (int i = 0; i < 11; i++) {
            canvas.drawLine(x, 0, x, length * 20, paint);
            x += length;
        }
        for (int j = 0; j < 21; j++) {
            canvas.drawLine(0, y, length * 10, y, paint);
            y += length;
        }
    }

    private void drawBlocks(Canvas canvas, Board board) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        int length = canvas.getWidth() / 12;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++) {
                char block = board.getGrid()[i][k];
                int x = k * length;
                int y = i * length;
                if (block != '.') {
                    paint.setColor(colorScheme.get(block));
                    canvas.drawRect(new Rect(x, y, x + length, y + length), paint);
                }
            }
        }
    }
}
