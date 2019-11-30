package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGamePointsSystem;

public class StatsDrawer {
    private Paint badStatsPaint;
    private Paint goodStatsPaint;

    public void setUpPaints() {
        goodStatsPaint = new Paint();
        goodStatsPaint.setTextSize(60);
        goodStatsPaint.setColor(Color.GREEN);

        badStatsPaint = new Paint();
        badStatsPaint.setTextSize(60);
        badStatsPaint.setColor(Color.RED);
    }

    public void drawStats(Canvas canvas, RhythmGamePointsSystem pointsSystem,
                          float left, float top, float right, float bottom) {
    }

    Paint getBadStatsPaint() {
        return badStatsPaint;
    }

    Paint getGoodStatsPaint() {
        return goodStatsPaint;
    }
}
