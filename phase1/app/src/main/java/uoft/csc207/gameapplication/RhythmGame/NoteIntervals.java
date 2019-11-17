package uoft.csc207.gameapplication.RhythmGame;

import android.accounts.AbstractAccountAuthenticator;

import java.util.ArrayList;

public class NoteIntervals {
    private ArrayList<Long> clickIntervals;
    private ArrayList<Long> clickTimes;
    private Long startTime;
    private Long previousClickTime;

    public NoteIntervals(){
        startTime = System.currentTimeMillis();
        previousClickTime = startTime;
        clickIntervals = new ArrayList<>();
        clickTimes = new ArrayList<>();
    }

    public void click() {
        clickTimes.add(System.currentTimeMillis());
        clickIntervals.add(System.currentTimeMillis() - previousClickTime);
        previousClickTime = System.currentTimeMillis();

    System.out.println("click times:" + clickTimes);
    System.out.println("click intervals:" + clickIntervals);

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
