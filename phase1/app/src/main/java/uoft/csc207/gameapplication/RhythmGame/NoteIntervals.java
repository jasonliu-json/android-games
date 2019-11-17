package uoft.csc207.gameapplication.RhythmGame;

import android.accounts.AbstractAccountAuthenticator;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class NoteIntervals {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Long> clickTimes;
    private Long startTime;
    private Long previousClickTime;
    private CsvWriter csvWriter;


    public NoteIntervals(){
        startTime = System.currentTimeMillis();
        previousClickTime = startTime;
        clickIntervals = new ArrayList<>();
        clickTimes = new ArrayList<>();
        csvWriter = new CsvWriter();


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
        csvWriter.writeToFile(intervalsList);

    }

    public static String[] arrayListToStringArray(ArrayList<Long> arr)
    {
        Object[] objArr = arr.toArray();
        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);
        return str;
    }

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
