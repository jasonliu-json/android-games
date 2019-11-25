package uoft.csc207.gameapplication.RhythmGame.NoteGenerator;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;

/**
 * Generates notes at certain intervals, where the column the note is generated in is random.
 */
public class RandomNoteGenerator extends NoteGenerator {
    public enum Difficulty { EASY, NORMAL, HARD, IMPOSSIBLE}
    private int noteGenerationPeriod = 1000;
    private static int refreshTime = 500;
    private long lastNoteTime;

    public RandomNoteGenerator(RhythmGame rhythmGame) {
        super(rhythmGame);
        setDifficulty(Difficulty.EASY);
        System.out.println("constructor in RNG");
    }

    public void timeUpdate() {
        System.out.println("timeUpdate in RNG");
        // Every period generate a note at a random column
        if (System.currentTimeMillis() - lastNoteTime >= noteGenerationPeriod) {
            getRhythmGame().generateNote((int) (getNumColumns() * Math.random()));
//            getRhythmGame().generateNote(1);
            lastNoteTime = System.currentTimeMillis();
        }

        // Changes difficulty based on points
        if (getRhythmGame().getPoints() > 100) setDifficulty(Difficulty.HARD);
        else if (getRhythmGame().getPoints() > 50) setDifficulty(Difficulty.NORMAL);
    }

    @Override
    public void start() {
        lastNoteTime = System.currentTimeMillis();
    }

    /**
     * Sets the difficulty of the game by generating notes more or less frequently
     * @param diff enum RhythmGame.Difficulty
     */
    private void setDifficulty(Difficulty diff) {
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
