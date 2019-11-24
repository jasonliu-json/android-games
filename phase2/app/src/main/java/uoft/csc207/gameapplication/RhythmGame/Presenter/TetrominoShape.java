package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.graphics.RectF;

/**
 * Consists of a tetromino and a height and width
 */
public class TetrominoShape {

    private Tetromino tetromino;
    private float height, width;
    private RectF[] unitFigure;

    public TetrominoShape(Tetromino tetromino) {
        this.tetromino = tetromino;
        this.unitFigure = generateFigure(tetromino.getCoords());
        this.height = tetromino.getHeight();
        this.width = tetromino.getWidth();
    }

    /**
     * Generates the figure based on the tetromino
     * @param coords the coorinates of the tetromino
     * @return an array of squares that corresponds to the shape of the tetromino
     */
    public static RectF[] generateFigure(Integer[][] coords) {
        RectF[] fourBlocks = new RectF[4];
        for (int i = 0; i < 4; i++) {
            int left = coords[i][0];
            int top = coords[i][1];
            fourBlocks[i] = new RectF(left, top, left + 1, top + 1);
        }
        return fourBlocks;
    }

    /**
     * Performs a deep copy of the figure
     * @return an array of squares that corresponds to the shape of the tetromino
     */
    public RectF[] getCopyOfUnitFigure() {
        RectF[] copy = new RectF[unitFigure.length];
        for (int i = 0; i < unitFigure.length; i++)
            copy[i] = new RectF(unitFigure[i]);
        return copy;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
