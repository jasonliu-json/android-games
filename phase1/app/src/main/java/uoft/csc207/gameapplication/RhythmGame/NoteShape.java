package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A shape where (0, 0) is the top left corner, and is defined by unit lengths
 */
class NoteShape {
    TetrominoShape tetroShape;
    private float scale;
    private RectF[] figure;

    public NoteShape(TetrominoShape tetroShape) {
        this.tetroShape = tetroShape;
        this.figure = tetroShape.getCopyOfUnitFigure();
        this.scale = 1;
    }

    public NoteShape(TetrominoShape tetroShape, float scale) {
        this.tetroShape = tetroShape;
        this.setScale(scale);
    }


    public float getHeight() {
        return tetroShape.getHeight() * scale;
    }

    public float getWidth() {
        return tetroShape.getWidth() * scale;
    }

    public void setScale(float sc) {
        this.scale = sc;
        RectF[] figureToRescale = tetroShape.getCopyOfUnitFigure();
        for (RectF r : figureToRescale) {
            r.set(r.left * sc, r.top*sc, r.right*sc, r.bottom *sc);
        }
        this.figure = figureToRescale;

    }

    public NoteShape clone() {
       return new NoteShape(this.tetroShape, this.scale);
    }

    public void draw(float x, float y, Canvas canvas, Paint paint) {
        // RectF[] unitFigure = getFigure();
        for (RectF rect : figure) {
            RectF toDrawRect = new RectF(x + rect.left, y + rect.top,
                    x + rect.right, y +rect.bottom);
            canvas.drawRect(toDrawRect, paint);
        }

    }
}
