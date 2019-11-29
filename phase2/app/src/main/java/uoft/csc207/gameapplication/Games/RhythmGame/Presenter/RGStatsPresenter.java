//package uoft.csc207.gameapplication.Games.RhythmGame.Presenter;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.util.DisplayMetrics;
//
//import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGameLevel;
//import uoft.csc207.gameapplication.Games.RhythmGame.GameLogic.RhythmGamePointsSystem;
//
//public class RGStatsPresenter extends RhythmGamePresenter {
//    private RhythmGamePointsSystem pointsSystem;
//
//    private Paint goodStatsPaint;
//    private Paint badStatsPaint;
//    private Paint upperRectPaint = new Paint();
//    private int textHeight;
//
//    public RGStatsPresenter(RhythmGameLevel rhythmGameLevel, DisplayMetrics metrics,
//                            Context context, char[] shapes) {
//        super(rhythmGameLevel, metrics, context, shapes);
//        pointsSystem = rhythmGameLevel.getPointsSystem();
//
//        // Change the height of the bitmap
//        Rect bounds = new Rect();
//        goodStatsPaint.getTextBounds("Perfect: ", 0, "Perfect".length(), bounds);
//        textHeight = bounds.height();
//        setBitmapTop(0);
//        initializeBitmap();
//    }
//
//
//    /**
//     * Sets up paints.
//     * @param shapes character array, where length is as long as numColumns
//     */
//    @Override
//    void setTheme(char[] shapes) {
//        super.setTheme(shapes);
//
//        upperRectPaint = new Paint();
//        upperRectPaint.setColor(Color.BLACK);
//
//        goodStatsPaint = new Paint();
//        goodStatsPaint.setTextSize(60);
//        goodStatsPaint.setColor(Color.GREEN);
//
//        badStatsPaint = new Paint();
//        badStatsPaint.setTextSize(60);
//        badStatsPaint.setColor(Color.RED);
//    }
//
//    /**
//     * Draws the game and displays the statistics.
//     * @param bitCanvas the canvas to draw on
//     */
//    @Override
//    void updateBitmap(Canvas bitCanvas) {
//        super.updateBitmap(bitCanvas);
//
//
//    }
//}
