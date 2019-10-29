package uoft.csc207.gameapplication.RhythmGame;
import java.util.*;

public class RunWithTimer extends Thread{

    private RhythmGameView rhythmGameView;
    private long startTime = System.currentTimeMillis();
    private long runTime = 20000;
    private long updateInterval = 100;

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
