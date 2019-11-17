package uoft.csc207.gameapplication.RhythmGame;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

public class RhythmGamePointsSystem implements Observer {
    public enum NoteEvent {PERFECT, GREAT, GOOD, BAD, MISSED};

    private int numPerfect, numGreat, numGood, numBad, numMissed = 0;
    private int points = 0;

    private Timer timer;

    @Override
    public void update(Observable observable, Object o) {
//        ((Column)observable).getId();

        switch((NoteEvent)o) {
            case PERFECT:
                numPerfect++;
                addPoints(10);
        System.out.println("perfect -----");
                break;
            case GREAT:
                numGreat++;
                addPoints(8);
        System.out.println("great----");
                break;
            case GOOD:
                numGood++;
                addPoints(5);
        System.out.println("good------");
                break;
            case BAD:
                numBad++;
                addPoints(-1);
        System.out.println("bad----");
                System.out.println(points);
                break;
            case MISSED:
                numMissed++;
                System.out.println(points);
        System.out.println("missed-----");
                break;
            default:
                System.out.println("Something is wrong");
                break;
        }
    }

    private void addPoints(int dPoints) {
        this.points = Math.max(0, this.points + dPoints);
    }

    public int getNumPerfect() {
        return numPerfect;
    }

    public int getNumGreat() {
        return numGreat;
    }

    public int getNumGood() {
        return numGood;
    }

    public int getNumBad() {
        return numBad;
    }

    public int getNumMissed() {
        return numMissed;
    }

    public int getPoints() {
        return points;
    }
}
