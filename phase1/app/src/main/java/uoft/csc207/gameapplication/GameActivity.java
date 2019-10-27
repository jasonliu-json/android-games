package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGameView;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameView;

public class GameActivity extends AppCompatActivity {

    private MazeGameView mazeView;
    private RhythmGameView rhythmGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//        mazeView = (MazeGameView) findViewById(R.id.MazeGameView);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        Intent returnToMenu = new Intent(GameActivity.this, Login.class);
//        mazeView.init(metrics, returnToMenu);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        rhythmGameView = (RhythmGameView) findViewById(R.id.RhythmGameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        rhythmGameView.init(metrics);
    }
}
