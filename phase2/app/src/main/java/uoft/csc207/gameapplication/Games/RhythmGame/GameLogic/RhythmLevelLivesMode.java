package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

/**
 * A Rhythm game level where the notes are generated in a random column.
 * The level ends when a max amount notes missed is reached.
 */
public class RhythmLevelLivesMode extends RhythmGameLevel {
    private RandomNoteGenerator noteGenerator;
    private int numLives = 10;

    public RhythmLevelLivesMode(int numColumns, int gameHeight, String song) {
        super(numColumns, gameHeight, song);

        noteGenerator = new RandomNoteGenerator();
        setNoteGenerator(noteGenerator);
    }

    /**
     * Updates the state of the game by one unit time.
     */
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

}
