package uoft.csc207.gameapplication.RhythmGame;
import java.util.*;

public class RunWithTimer extends Thread{

    private RhythmGameView rhythmGameView;
    private static long startTime = System.currentTimeMillis();

//    private static long runTime = RhythmGame.runTime;
//    private static long updateInterval = RhythmGame.updateInterval;

    public RunWithTimer(RhythmGameView rhythmGameDriver) {
        this.rhythmGameView = rhythmGameDriver;
    }


    public void run() {

        RhythmGame.setDifficulty("NORMAL");
        while (System.currentTimeMillis() - startTime < RhythmGame.runTime ) {

            try {
                rhythmGameView.update();
                rhythmGameView.invalidate();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                this.sleep(RhythmGame.updateInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
