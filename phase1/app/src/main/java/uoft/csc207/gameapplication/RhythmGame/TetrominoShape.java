package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Consists of a tetromino and height and width
 */
public class TetrominoShape {

    private Tetromino tetro;

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    private float height, width;

    private RectF[] unitFigure;

    public RectF[] getCopyOfUnitFigure() {
        RectF[] copy = new RectF[unitFigure.length];
        for (int i = 0; i < unitFigure.length; i++)
            copy[i] = new RectF(unitFigure[i]);
        return copy;
    }

    public TetrominoShape(Tetromino tetro) {
        this.tetro = tetro;
        this.unitFigure = generateFigure(tetro.getCoords());
        this.height = tetro.getHeight();
        this.width = tetro.getWidth();
    }

    public static RectF[] generateFigure(int[][] coords) {
        RectF[] fourBlocks = new RectF[4];
        for (int i  = 0; i < 4; i++) {
            int left = coords[i][0];
            int top = coords[i][1];
            fourBlocks[i] = new RectF(left, top, left + 1, top + 1);
        }
        return fourBlocks;
    }
}
