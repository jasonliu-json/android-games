package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

/**
 * Generates notes at certain intervals, where the column the note is generated in is random.
 */
public class RandomNoteGenerator extends NoteGenerator {
    public enum Difficulty { EASY, NORMAL, HARD, IMPOSSIBLE}
    private int noteGenerationPeriod = 1000;
    private static int refreshTime = 500;
    private long lastNoteTime;

    public RandomNoteGenerator() {
        setDifficulty(Difficulty.EASY);
    }

    @Override
    public void timeUpdate(Column[] columns) {
        // Every period generate a note at a random column
        if (System.currentTimeMillis() - lastNoteTime >= noteGenerationPeriod) {
            columns[(int) (columns.length * Math.random())].generateNote();
            lastNoteTime = System.currentTimeMillis();
        }
    }

    @Override
    public void start() {
        lastNoteTime = System.currentTimeMillis();
    }

    /**
     * Sets the difficulty of the game by generating notes more or less frequently
     * @param diff enum RhythmGameLevel.Difficulty
     */
    void setDifficulty(Difficulty diff) {
        switch(diff) {
            case EASY:
                noteGenerationPeriod = 900;
                break;
            case NORMAL:
                noteGenerationPeriod = 700;
                break;
            case HARD:
                noteGenerationPeriod = 500;
                break;
            case IMPOSSIBLE:
                noteGenerationPeriod = 200;
                refreshTime = 100;
                break;
            default:
                noteGenerationPeriod = 1000;
                break;
        }
    }

    public static int getRefreshTime() {return refreshTime;}
}
