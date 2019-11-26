package uoft.csc207.gameapplication.RhythmGame.NoteGenerator;

import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Arrays;

import uoft.csc207.gameapplication.R;
import uoft.csc207.gameapplication.RhythmGame.GameLogic.RhythmGame;
import uoft.csc207.gameapplication.RhythmGame.Presenter.RhythmGamePresenter;


/**
 * Generates notes based on the rhythm of the song
 */
public class SongNoteGenerator extends NoteGenerator {

    ArrayList<Long> noteIntervals;

    private int intervalIndex = 0;
    private Long lastNoteTime;
    public RhythmGamePresenter.Song song;

    public SongNoteGenerator(RhythmGame rhythmGame) {
        super(rhythmGame);

        System.out.println("constructor in SONG");

        noteIntervals = new ArrayList<>();



        // gonna hard code an array list for now
//
        String[] testingIntervals = new String[10];
        for (int i=0;i<10;i++){
            noteIntervals.add(Long.valueOf(i));
        }

        System.out.println("constructor note intervals" + noteIntervals);

    }


    @Override
    public void start() {
        lastNoteTime = System.currentTimeMillis();
    }

    public void timeUpdate() {

        System.out.println("timeUpdate in SONG");
        Long currentInterval = (System.currentTimeMillis() - lastNoteTime) / 1000;

        if (noteIntervals != null && intervalIndex < noteIntervals.size() && currentInterval >= noteIntervals.get(intervalIndex)) {
            getRhythmGame().generateNote((int) (getNumColumns() * Math.random()));
            lastNoteTime = System.currentTimeMillis();
            intervalIndex += 1;

            System.out.println(noteIntervals);
            System.out.println("next interval");
        }

    }


}
