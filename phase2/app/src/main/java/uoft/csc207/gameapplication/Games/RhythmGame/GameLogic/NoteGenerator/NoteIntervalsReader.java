package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


class NoteIntervalsReader {
    private List<Double> clickIntervals;
    private List<Integer> noteColumns;
    private Context context;

    NoteIntervalsReader(Context context, String song) {
        clickIntervals = new ArrayList<>();
        noteColumns = new ArrayList<>();
        this.context = context;
        read(song);
    }


    List<Double> getIntervalsArray() {
        return clickIntervals;
    }

    List<Integer> getNoteColumns() {
        return noteColumns;
    }

    private void read(String song) {

        String fileName = "old_town_road_intervals.csv";
        if (song.equalsIgnoreCase("Old Town Road")) {
            fileName = "old_town_road_intervals.csv";
        } else if (song.equalsIgnoreCase("Mii Channel")) {
            fileName = "mii_channel_intervals.csv";
        }

        try (InputStream raw = context.getAssets().open(fileName)) {
            BufferedReader is = new BufferedReader(new InputStreamReader(raw, StandardCharsets.UTF_8));
            String currentLine = is.readLine();
            while (currentLine != null) {
                System.out.println(currentLine);
                String[] pair = currentLine.split(",");

                double interval = Double.valueOf(pair[0]);
                Integer column = Integer.valueOf(pair[1].trim());

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