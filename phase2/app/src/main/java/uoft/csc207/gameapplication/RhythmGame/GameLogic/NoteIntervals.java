package uoft.csc207.gameapplication.RhythmGame.GameLogic;

import android.accounts.AbstractAccountAuthenticator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NoteIntervals {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Long> clickTimes;
    private String filePath;
    private ArrayList<Integer> intervalsArray;
    private Long firstInterval;


    public NoteIntervals(String path){
        filePath = path;
        clickIntervals = generateIntervalsArray(filePath);
        clickTimes = new ArrayList<>();
        firstInterval = calculateFirstInterval();


    }

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

    public ArrayList<Long> generateIntervalsArray(String path) {
        ArrayList<Long> list = new ArrayList<>();
        list.add(firstInterval);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String s: br.readLine().split(",")) {
                list.add(Long.valueOf(s));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(list);
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