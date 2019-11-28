package uoft.csc207.gameapplication.RhythmGame.GameLogic;

/**
 * A Rhythm game level where notes are generated based on the song.
 * The level ends when the song is over.
 */
public class RhythmLevelSongMode extends RhythmGameLevel {
    private SongNoteGenerator noteGenerator;

    public RhythmLevelSongMode(int numColumns, int gameHeight, String song) {
        super(numColumns, gameHeight, song);

        noteGenerator = new SongNoteGenerator(song);
        setNoteGenerator(noteGenerator);
    }

    @Override
    public void timeUpdate() {
        super.timeUpdate();
        // Changes difficulty based on points

        // Checks if the game is over
        if (noteGenerator.getIsOver()) {
            // If no more notes in all columns, then game is over
            for (Column col : getColumns())
                if (!col.getNotes().isEmpty()) return;

            gameOver();
        }
    }

    @Override
    public SongNoteGenerator getNoteGenerator() {
        return noteGenerator;
    }

}
