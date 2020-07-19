package uoft.csc207.gameapplication.RhythmGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class IntervalsGenerator {

    private String filePath = "/Users/jason-pc/Documents/groupproject/phase1/app/src/main/java/uoft/csc207/gameapplication/RhythmGame/Intervals.csv";
    private File file = new File(filePath);
//    private BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

    private ArrayList<Integer> intervalsArray;

    private Integer firstInterval;

    public IntervalsGenerator() {

        intervalsArray = convertFileToArray(file);
        firstInterval = calculateFirstInterval();
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
}
