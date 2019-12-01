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
    private Long lastNoteTime;
    private Context context;
    public String song;


    private boolean isOver = false;

    public SongNoteGenerator(String song, Context context) {

        this.context = context;
        NoteIntervalsReader noteIntervalsReader = new NoteIntervalsReader(context, song);
        noteIntervals = noteIntervalsReader.getIntervalsArray();
        noteColumns = noteIntervalsReader.getNoteColumns();

    }

    @Override
    public void start() {
        lastNoteTime = System.currentTimeMillis();
    }

    @Override
    public void timeUpdate(Column[] columns) {

//        System.out.println("timeUpdate in SONG");
        Long currentInterval = (System.currentTimeMillis() - lastNoteTime) / 1000;

        if (noteIntervals != null && intervalIndex < noteIntervals.size() && currentInterval >= noteIntervals.get(intervalIndex)) {
            columns[((int) (columns.length * Math.random()))].generateNote();
            lastNoteTime = System.currentTimeMillis();
            intervalIndex += 1;

            System.out.println(noteIntervals);
//            System.out.println("next interval");
        }

        if (intervalIndex >= noteIntervals.size()) isOver = true;
    }

    public boolean getIsOver() {
        return isOver;
    }


}
