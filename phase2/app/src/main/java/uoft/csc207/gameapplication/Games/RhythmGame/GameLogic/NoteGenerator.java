package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

/**
 * Generates notes in the Rhythm columns
 */
public abstract class NoteGenerator {
    public abstract void timeUpdate(Column[] columns);

    public void start(){}

    public void stop(){}
}
