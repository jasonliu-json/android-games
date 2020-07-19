package uoft.csc207.gameapplication.RhythmGame;

import android.content.Context;

public class RhythmGameBuilder {
    private Context context;
    private int numColumns;

    public RhythmGameBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public RhythmGameBuilder setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        return this;
    }

    public RhythmGame createRhythmGame() {
        return new RhythmGame(context, numColumns);
    }
}