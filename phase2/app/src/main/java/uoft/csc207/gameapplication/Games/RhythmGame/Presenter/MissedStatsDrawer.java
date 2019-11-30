package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;

import android.graphics.Canvas;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGamePointsSystem;

public class MissedStatsDrawer extends StatsDrawer {
    @Override
    public void drawStats(Canvas canvas, RhythmGamePointsSystem pointsSystem,
                          float left, float top, float right, float bottom) {
        canvas.drawText("Missed: " + pointsSystem.getNumMissed(), (left + right) /2,
                (top + bottom) / 2, getBadStatsPaint());
    }
}
