package uoft.csc207.gameapplication.RhythmGame.NoteGenerator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NoteIntervalsReader {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Long> clickTimes;
    private ArrayList<Integer> intervalsArray;
    private Long firstInterval;

    public static String filePath;



    public NoteIntervalsReader() {
//        filePath = "mii_channel_intervals.csv";
//        clickIntervals = generateIntervalsArray();
        clickTimes = new ArrayList<>();
        firstInterval = calculateFirstInterval();

    }

    public static void setPath(String newPath) {filePath = newPath;}

//
//    public static String[] arrayListToStringArray(ArrayList<Long> arr)
//    {
//        Object[] objArr = arr.toArray();
//        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);
//        return str;
//    }

    public Long calculateFirstInterval() {
        return firstInterval;
    }

    public ArrayList<Long> generateIntervalsArray() {


        try (BufferedReader brTest = new BufferedReader(new FileReader(filePath))) {
            String text = brTest.readLine();
// Stop. text is the first line.
            System.out.println(text);
            String[] strArray = text.split(",");
            System.out.println("heres the array");
            System.out.println(Arrays.toString(strArray));
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("io exception");
        }

        return new ArrayList<>();

    }



//    public ArrayList<Long> generateIntervalsArray(String path) {
////        ArrayList<Long> list = new ArrayList<>();
//        clickIntervals.add(firstInterval);
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            for (String s: br.readLine().split(",")) {
//                clickIntervals.add(Long.valueOf(s));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("arraylist: ");
//        System.out.println(clickIntervals);
//        return clickIntervals;
//
//
//    }



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