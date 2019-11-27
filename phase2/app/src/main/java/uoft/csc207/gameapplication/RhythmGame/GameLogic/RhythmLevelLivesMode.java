package uoft.csc207.gameapplication.RhythmGame.GameLogic;

public class RhythmLevelLivesMode extends RhythmGameLevel {
    private RandomNoteGenerator noteGenerator;
    private int numLives = 10;

    public RhythmLevelLivesMode(int numColumns, int gameHeight, String song) {
        super(numColumns, gameHeight, song);

        noteGenerator = new RandomNoteGenerator();
    }

    @Override
    public void timeUpdate() {
        super.timeUpdate();
        // Changes difficulty based on points
        if (getPoints() > 100) noteGenerator.setDifficulty(RandomNoteGenerator.Difficulty.HARD);
        else if (getPoints() > 50) noteGenerator.setDifficulty(RandomNoteGenerator.Difficulty.NORMAL);

        // Checks if the game should be over
        if (getNumMissed() >= numLives) {
            gameOver();
        }
    }

    @Override
    public NoteGenerator getNoteGenerator() {
        return noteGenerator;
    }
}
