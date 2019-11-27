package uoft.csc207.gameapplication.RhythmGame.GameLogic;

public class RhythmLevelSongMode extends RhythmGameLevel {
    private SongNoteGenerator noteGenerator;

    public RhythmLevelSongMode(int numColumns, int gameHeight, String song) {
        super(numColumns, gameHeight, song);

        noteGenerator = new SongNoteGenerator(song);
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
