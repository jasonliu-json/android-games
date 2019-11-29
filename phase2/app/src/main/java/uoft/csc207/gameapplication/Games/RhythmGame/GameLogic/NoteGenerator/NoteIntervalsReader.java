package uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.NoteGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NoteIntervalsReader {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Integer> noteColumns;
    private ArrayList<Integer> intervalsArray;
    private Long firstInterval;

    public static String filePath;



    public NoteIntervalsReader() {
//        filePath = "mii_channel_intervals.csv";
        clickIntervals = new ArrayList<>();
        firstInterval = calculateFirstInterval();

    }

    public static void setPath(String newPath) {filePath = newPath;}

    public Long calculateFirstInterval() {
        return firstInterval;
    }

    public ArrayList<Long> generateIntervalsArray(String path) {

        clickIntervals.add(firstInterval);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String currentLine = br.readLine();
            while (currentLine != null) {
                Long interval = Long.valueOf(currentLine.split(",")[0]);
                Integer column = Integer.valueOf(currentLine.split(",")[1]);

                clickIntervals.add(interval);
                noteColumns.add(column);

                currentLine = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("arraylist: ");
        System.out.println(clickIntervals);
        return clickIntervals;


    }



//
//    public static void writeToFile(String[] args) {
//
//        try (PrintWriter writer = new PrintWriter(intervalsFile)) {
//
//            StringBuilder sb = new StringBuilder();
//
//            for (int i=0; i<args.length; i++) {
//                sb.append(args[i]);
//                sb.append(", ");
//            }
//            sb.append('\n');
//
//            writer.write(sb.toString());
//
//            System.out.println("done!");
//
//
//
//    }

}

//    public void addCurrentTime() {
//        clickTimes.add(System.currentTimeMillis());
//    }
//
//    public void addCurrentInterval() {
//        clickIntervals.add(System.currentTimeMillis() - previousClickTime);
//    }
//
//}