package uoft.csc207.gameapplication.RhythmGame;
import java.util.*;

public class RunWithTimer extends Thread{

    private RhythmGameView rhythmGameView;
    private static long startTime = System.currentTimeMillis();
    private static long runTime = 500000;
    private static long updateInterval = RhythmGame.updateInterval;

    public RunWithTimer(RhythmGameView rhythmGameDriver) {
        this.rhythmGameView = rhythmGameDriver;
    }


    public void run() {

        while (System.currentTimeMillis() - startTime < runTime ) {

            try {
                this.rhythmGameView.update();
                rhythmGameView.invalidate();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                this.sleep(updateInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
