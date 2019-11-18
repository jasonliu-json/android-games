package uoft.csc207.gameapplication.RhythmGame;

import android.accounts.AbstractAccountAuthenticator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class NoteIntervals {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Long> clickTimes;
    private Long startTime;
    private Long previousClickTime;
    private PrintWriter writer;
    public static File intervalsFile = new File("Intervals.csv");
    private ArrayList<Integer> intervalsArray;
    private Integer firstInterval;


    public NoteIntervals(){
        startTime = System.currentTimeMillis();
        previousClickTime = startTime;
        clickIntervals = new ArrayList<>();
        clickTimes = new ArrayList<>();
        firstInterval = calculateFirstInterval();

        try (PrintWriter writer = new PrintWriter(intervalsFile)) {

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void click() {

        Long currentTime = System.currentTimeMillis();
        clickTimes.add(currentTime);
        clickIntervals.add(currentTime - previousClickTime);
        previousClickTime = currentTime;

    System.out.println("click times:" + clickTimes);
    System.out.println("click intervals:" + clickIntervals);

    }

    public void writeIntervalsToFile() {
        String [] intervalsList = arrayListToStringArray(clickIntervals);
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<intervalsList.length; i++) {
            sb.append(intervalsList[i]);
            sb.append(", ");
        }
        sb.append('\n');
        writer.write(sb.toString());
        System.out.println("done!");
    }

    public static String[] arrayListToStringArray(ArrayList<Long> arr)
    {
        Object[] objArr = arr.toArray();
        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);
        return str;
    }

    public Integer calculateFirstInterval() {
        return firstInterval;
    }

    public ArrayList<Integer> convertFileToArray(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(firstInterval);


        // add stuff from the csv
        return list;
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
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
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
