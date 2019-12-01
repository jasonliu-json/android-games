package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import uoft.csc207.gameapplication.R;

public class NoteIntervalsReader {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Integer> noteColumns;
    private Context context;

    public NoteIntervalsReader(Context context, String song) {
        clickIntervals = new ArrayList<>();
        noteColumns = new ArrayList<>();
        this.context = context;
        read(song);
    }


    public ArrayList<Long> getIntervalsArray() {
        return clickIntervals;
    }

    public ArrayList<Integer> getNoteColumns() {
        return noteColumns;
    }

    public void read(String song) {

        String fileName = "old_town_road_intervals.csv";
        if (song.equals("Old Town Road")) {
            fileName = "old_town_road_intervals.csv";
        } else if (song.equals("Mii Channel")) {
            fileName = "mii_channel_intervals.csv";
        }

        try (InputStream raw = context.getAssets().open(fileName)) {
            BufferedReader is = new BufferedReader(new InputStreamReader(raw, "UTF8"));
            String currentLine = is.readLine();
            while (currentLine != null) {
                Long interval = Long.valueOf(currentLine.split(",")[0]);
                Integer column = Integer.valueOf(currentLine.split(",")[1]);

                clickIntervals.add(interval);
                noteColumns.add(column);

                currentLine = is.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("arraylist: ");
        System.out.println(clickIntervals);

    }
}