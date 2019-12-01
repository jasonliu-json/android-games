package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.Column.Column;


/**
 * Generates notes based on the rhythm of the song
 */
public class SongNoteGenerator extends NoteGenerator {

//    ArrayList<Long> noteIntervals;
    ArrayList<Integer> noteColumns;
    private List<Long> noteIntervals;

    private NoteIntervalsReader intervalsReader;

    private int intervalIndex = 0;
    private int lastNoteTime;
    private String song;
    private boolean isRunning;

    private boolean isOver = false;

    public SongNoteGenerator(Column[] columns, String song, Context context) {
        super(columns);
        NoteIntervalsReader noteIntervalsReader = new NoteIntervalsReader(context, song);
        noteIntervals = noteIntervalsReader.getIntervalsArray();
        noteColumns = noteIntervalsReader.getNoteColumns();
    }

    /**
     * Starts and allows note generation.
     */
    @Override
    public void start() {
        lastNoteTime = 0;
        isRunning = true;
    }

    /**
     * Updates it by one unit time, if allowed.
     */
    @Override
    public void timeUpdate() {
        if (isRunning) {
            lastNoteTime += 30;

    //        System.out.println("timeUpdate in SONG");

            if (noteIntervals != null && intervalIndex < noteIntervals.size() && lastNoteTime >= noteIntervals.get(intervalIndex)) {
                getColumns()[((int) (getColumns().length * Math.random()))].generateNote();
                lastNoteTime = 0;
                intervalIndex += 1;

                System.out.println(noteIntervals);
    //            System.out.println("next interval");
            }

            if (intervalIndex >= noteIntervals.size()) isOver = true;
        }
    }

    /**
     * Pauses note generation.
     */
    @Override
    public void stop() {
        isRunning = false;
    }

    /**
     * Returns whether the generator is done generating notes.
     * @return true if and only if the generator has no more notes to generate.
     */
    @Override
    public boolean getIsOver() {
        return isOver;
    }
}
