package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGameView;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameView;
import uoft.csc207.gameapplication.TetrisGame.TetrisGameView;

public class GameActivity extends AppCompatActivity {

    private MazeGameView mazeView;
    private RhythmGameView rhythmGameView;
    private TetrisGameView tetrisGameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//        mazeView = (MazeGameView) findViewById(R.id.MazeGameView);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        Intent returnToMenu = new Intent(GameActivity.this, Login.class);
//        mazeView.init(metrics, returnToMenu);

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//        rhythmGameView = (RhythmGameView) findViewById(R.id.RhythmGameView);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        rhythmGameView.init(metrics);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tetrisGameView = (TetrisGameView) findViewById(R.id.TetrisGameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        tetrisGameView.init(metrics);

//        float startTime = System.currentTimeMillis();
//        float timeSinceUpdate = System.currentTimeMillis();
//
//        while (true) {
//            if (timeSinceUpdate > 5000) {
//                rhythmGameView.invalidate();
//                rhythmGameView.update();
//                timeSinceUpdate = 0;
//            }
//        }


    }
}
