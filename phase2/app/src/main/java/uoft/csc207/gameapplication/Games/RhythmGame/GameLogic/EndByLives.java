package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

/**
 * Determines that the level is over based on a maximum number of notes missed.
 */
public class EndByLives extends EndRhythmLevelStrategy {
    private int numLives;
    private RhythmGamePointsSystem pointsSystem;

    EndByLives(int numLives, RhythmGamePointsSystem pointsSystem) {
        this.numLives = numLives;
        this.pointsSystem = pointsSystem;
    }

    @Override
    public boolean getIsLevelOver() {
        return pointsSystem.getNumMissed() >= numLives;
    }
}
