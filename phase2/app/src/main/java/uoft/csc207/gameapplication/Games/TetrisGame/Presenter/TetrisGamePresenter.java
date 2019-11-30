package uoft.csc207.gameapplication.Games.TetrisGame.Presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Map;

import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameMediator;


public class TetrisGamePresenter {

    private TetrisGameMediator mediator;
    private String colorScheme;
    private Map<String, Map<Character, Integer>> colorSchemes;


    public TetrisGamePresenter(TetrisGameMediator mediator, String config) {
        this.mediator = mediator;
        colorScheme = config;
        colorSchemes = new HashMap<>();
        Map<Character, Integer> defaultScheme = new HashMap<>();
        defaultScheme.put('I', Color.rgb(130, 215, 255));
        defaultScheme.put('J', Color.rgb(100, 170, 255));
        defaultScheme.put('L', Color.rgb(255, 170, 70));
        defaultScheme.put('O', Color.rgb(255, 220, 100));
        defaultScheme.put('S', Color.rgb(155, 255, 110));
        defaultScheme.put('Z', Color.rgb(255, 100, 100));
        defaultScheme.put('T', Color.rgb(170, 140, 255));
        colorSchemes.put("default", defaultScheme);
        Map<Character, Integer> aquaScheme = new HashMap<>();
        aquaScheme.put('I', Color.rgb(130, 215, 255));
        aquaScheme.put('J', Color.rgb(130, 215, 255));
        aquaScheme.put('L', Color.rgb(130, 215, 255));
        aquaScheme.put('O', Color.rgb(130, 215, 255));
        aquaScheme.put('S', Color.rgb(130, 215, 255));
        aquaScheme.put('Z', Color.rgb(130, 215, 255));
        aquaScheme.put('T', Color.rgb(130, 215, 255));
        colorSchemes.put("default", aquaScheme);
        Map<Character, Integer> emberScheme = new HashMap<>();
        emberScheme.put('I', Color.rgb(255, 100, 100));
        emberScheme.put('J', Color.rgb(255, 100, 100));
        emberScheme.put('L', Color.rgb(255, 100, 100));
        emberScheme.put('O', Color.rgb(255, 100, 100));
        emberScheme.put('S', Color.rgb(255, 100, 100));
        emberScheme.put('Z', Color.rgb(255, 100, 100));
        emberScheme.put('T', Color.rgb(255, 100, 100));
        colorSchemes.put("default", emberScheme);
    }

    public void draw(Canvas canvas, Bitmap bitmap) {
        Canvas newCanvas = new Canvas(bitmap);
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);
        drawGrid(newCanvas, mediator.getBoard());
        drawBlocks(newCanvas, mediator.getBoard());
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
                    paint.setColor(colorSchemes.get(colorScheme).get(block));
                    canvas.drawRect(new Rect(x, y, x + length, y + length), paint);
                }
            }
        }
    }
}
