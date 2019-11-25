package uoft.csc207.gameapplication.RhythmGame.NoteGenerator;

import java.util.ArrayList;

import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;

public class NoteGenerator {
    private RhythmGame rhythmGame;
    private int numColumns;

    private NoteIntervalsReader noteIntervals;

    private ArrayList<Long> intervalsArray;


    public NoteGenerator(RhythmGame rhythmGame) {
        this.rhythmGame = rhythmGame;
        this.numColumns = rhythmGame.getNumColumns();
    }


    public void start() {

    }

    public void timeUpdate() {

    }

    public void stop() {

    }

    public RhythmGame getRhythmGame() {
        return rhythmGame;
    }

    public int getNumColumns() {
        return numColumns;
    }
}
