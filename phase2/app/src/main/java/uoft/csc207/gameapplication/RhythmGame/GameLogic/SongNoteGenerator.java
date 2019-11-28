package uoft.csc207.gameapplication.RhythmGame.GameLogic;

import java.util.ArrayList;
import java.util.List;


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
    public String song;

    private boolean isOver = false;

    public SongNoteGenerator(String song) {

//        System.out.println("constructor in SONG");

        noteIntervals = new ArrayList<>();
        noteColumns = new ArrayList<>();
        NoteIntervalsReader noteIntervalsReader = new NoteIntervalsReader();


        // gonna hard code an array list for now
//
        String[] testingIntervals = new String[10];
        for (int i=0;i<10;i++){
            noteIntervals.add(Long.valueOf(i));
            noteColumns.add(i % 4);
        }

//        System.out.println("constructor note intervals" + noteIntervals);
//        System.out.println("constructor note intervals" + noteIntervals);
//        System.out.println("columns" + noteColumns);


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
    }

    public boolean getIsOver() {
        return isOver;
    }


}
