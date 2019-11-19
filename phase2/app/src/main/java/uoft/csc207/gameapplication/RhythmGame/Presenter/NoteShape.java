package uoft.csc207.gameapplication.RhythmGame.Presenter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A shape where (0, 0) is the top left corner
 */
public class NoteShape {
    private TetrominoShape tetroShape;
    private float scale;
    private RectF[] figure;

    /**
     * Constructs a note shape based on a tetromino
     * @param tetroShape the tetromine shape
     */
    public NoteShape(TetrominoShape tetroShape) {
        this.tetroShape = tetroShape;
        this.setScale(1);
    }

    /**
     * Constructs a note shape based on a tetromino and scaling factor
     * @param tetroShape the tetromine shape
     * @param scale the scaling factor - new size of a unit length
     */
    private NoteShape(TetrominoShape tetroShape, float scale) {
        this.tetroShape = tetroShape;
        this.setScale(scale);
    }

    public NoteShape clone() {
        return new NoteShape(this.tetroShape, this.scale);
    }

    /**
     * Draws the note shape on the given canvas.
     * @param canvas to draw on
     * @param x position of the left corner on the canvas
     * @param y position of the top corner on the canvas
     * @param paint of shape
     */
    public void draw(Canvas canvas, float x, float y, Paint paint) {
        for (RectF rect : figure) {
            RectF toDrawRect = new RectF(x + rect.left, y + rect.top,
                    x + rect.right, y + rect.bottom);
            canvas.drawRect(toDrawRect, paint);

            // Draws the outline
            Paint strokePaint = new Paint();
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(2);
            canvas.drawRect(toDrawRect, strokePaint);
        }
    }

    /**
     * Changes the figure of this shape by making the unit length the scale
     *
     * @param sc scaling factor
     */
    public void setScale(float sc) {
        this.scale = sc;
        RectF[] figureToRescale = tetroShape.getCopyOfUnitFigure();
        for (RectF r : figureToRescale) {
            r.set(r.left * sc, r.top * sc, r.right * sc, r.bottom * sc);
        }
        this.figure = figureToRescale;
    }

    public float getHeight() {
        return tetroShape.getHeight() * scale;
    }

    float getWidth() {
        return tetroShape.getWidth() * scale;
    }
}
