package uoft.csc207.gameapplication.RhythmGame;

        import android.graphics.Canvas;
        import android.graphics.Paint;

        import java.util.ArrayList;

/**
 * A column of the rhythm, which consists of a shadow (the target)
 * and the notes to hit within the column.
 */
class Column {
    // the x position of the most left edge of the column
    private int x;
    private int width;
    private int height;

    // the shape and colour of the notes
    private NoteShape unitNoteShape;
    private Paint paint;

    int spacing = 20;
    int shadowWidth = width - 2*spacing;
    // the target
    private NoteShadow shadow;

    int allowedError = 5;
    int noteWidth = shadowWidth - 2*allowedError;
    private NoteShape columnNoteShape;
    // the notes
    private ArrayList<Note> notes = new ArrayList<>();

    public Column(int x, int width, int height, NoteShape unitNoteShape, Paint paint) {
        this.x = x;
        this.width = width;
        this.height = height;
        this.unitNoteShape = unitNoteShape;
        this.paint = paint;


        NoteShape shadowShape = unitNoteShape.clone();
        shadowShape.setScale(shadowWidth);
        this.shadow = new NoteShadow(x + spacing, 15, shadowShape);

        columnNoteShape = unitNoteShape.clone();
        columnNoteShape.setScale(noteWidth);

        notes.add(new Note(x + spacing + allowedError, height/2, columnNoteShape, paint));
    }

    public void update() {
        // move notes up
        for (Note note : notes) note.moveUp(1);
        // check to see if any notes are off screen and remove them
        //notes.add(new Note(x + spacing + allowedError, height, columnNoteShape, paint));
    }

    public void draw(Canvas canvas) {
        // draw Shadow
        // draw Notes
        for (Note note : notes) note.draw(canvas);
    }

    public void generateNote() {
        //notes.add(new Note());
    }

    public void tap() {
        // check to see if top note is in target
        // if in target remove note and update points
    }
}
