package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Paint;

class Target {
    private int y;
    private int allowedError;

    public Target(int y, int allowedError) {
        this.y = y;
        this.allowedError = allowedError;
    }

    public boolean contains(int y) {
        return Math.abs(y - this.y) <= allowedError;
    }

    public void draw(Canvas canvas, float x, float heightScale, NoteShape shape, Paint paint) {
        shape.draw(canvas, x, y * heightScale, paint);
    }
}
