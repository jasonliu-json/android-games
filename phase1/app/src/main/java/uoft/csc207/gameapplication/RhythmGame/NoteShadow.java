package uoft.csc207.gameapplication.RhythmGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class NoteShadow {
    private int x, y;
    private Paint paint;
    private NoteShape shape;

    public NoteShadow(int x, int y, NoteShape shape) {
        this.x = x;
        this.y = y;
        this.shape = shape.clone();
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    public void draw(Canvas canvas) {
        shape.draw(x, y, canvas, paint);
    }
}
