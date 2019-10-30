package uoft.csc207.gameapplication.RhythmGame;

class RhythmGameMessage {

    private String message;

    private int numIterExisted = 0;

    RhythmGameMessage (String msg) {
        this.message = msg;
    }

    String getMessage() {
        return message;
    }

    void incrementNumIterationsExisted() {
        numIterExisted += 1;
    }

    int getNumIterExisted() {
        return numIterExisted;
    }

}
