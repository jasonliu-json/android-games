package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic;

public class RhythmGameMessage {

    private String message;

    // the number of iterations it has existed in the column
    private int numIterExisted = 0;

    RhythmGameMessage (String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    void incrementNumIterationsExisted() {
        numIterExisted += 1;
    }

    int getNumIterExisted() {
        return numIterExisted;
    }

}
