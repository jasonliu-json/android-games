package uoft.csc207.gameapplication.Games.TetrisGame.Presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Map;

/**
 * A class responsible for presenting the Tetris game to the screen.
 */
public class TetrisGamePresenter {

    /**
     * The color scheme for the Tetris blocks.
     */
    private Map<String, Integer> colorScheme;

    /**
     * A map between the colors defined in the color scheme to the specifc blocks in Tetris.
     */
    private Map<Character, String> pieceToColor;

    /**
     * Construct a new TetrisGamePresenter object.
     *
     * @param colorScheme The color scheme for the Tetris blocks.
     */
    public TetrisGamePresenter(Map<String, Integer> colorScheme) {
        this.colorScheme = colorScheme;

        pieceToColor = new HashMap<>();
        pieceToColor.put('I', "BlockColour1");
        pieceToColor.put('J', "BlockColour2");
        pieceToColor.put('L', "BlockColour3");
        pieceToColor.put('O', "BlockColour4");
        pieceToColor.put('S', "BlockColour5");
        pieceToColor.put('Z', "BlockColour6");
        pieceToColor.put('T', "BlockColour7");
    }

    /**
     * Draw a frame of Tetris onscreen.
     *
     * @param canvas The canvas to be displayed.
     * @param bitmap The bitmap for drawing.
     * @param grid The 2D array representation of a board to be displayed.
     */
    public void draw(Canvas canvas, Bitmap bitmap, char[][] grid) {
        Canvas newCanvas = new Canvas(bitmap);
        newCanvas.save();
        newCanvas.drawColor(Color.WHITE);
        drawGrid(newCanvas, grid);
        drawBlocks(newCanvas, grid);
        canvas.drawBitmap(bitmap, 88, 88, null);
        newCanvas.restore();
    }

    /**
     * Draw a grid onscreen.
     *
     * @param canvas The canvas to be displayed.
     * @param grid The 2D array representation of a board to be displayed.
     */
    private void drawGrid(Canvas canvas, char[][] grid) {
        float length = canvas.getWidth() / (grid[0].length + 2);
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

    /**
     * Draw Tetris blocks onscreen.
     *
     * @param canvas The canvas to be displayed.
     * @param grid The 2D array representation of a board to be displayed.
     */
    private void drawBlocks(Canvas canvas, char[][] grid) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        int length = canvas.getWidth() / 12;
        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid[0].length; k++) {
                char block = grid[i][k];
                int x = k * length;
                int y = i * length;
                if (block != '.') {
                    Integer color = colorScheme.get(pieceToColor.get(block));
                    if (color != null) {
                        paint.setColor(color);
                    }
                    canvas.drawRect(new Rect(x, y, x + length, y + length), paint);
                }
            }
        }
    }
}
