package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.Toast;

import uoft.csc207.gameapplication.MazeGame.MazeGameView;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameView;
import uoft.csc207.gameapplication.TetrisGame.TetrisGameView;

public class GameActivity extends AppCompatActivity {

    private MazeGameView mazeView;
    private RhythmGameView rhythmGameView;
    private TetrisGameView tetrisGameView;

    public void generateToast(String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent returnToMenu = new Intent(GameActivity.this, Login.class);

        setContentView(R.layout.activity_game);
        mazeView = (MazeGameView) findViewById(R.id.MazeGameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mazeView.init(metrics);

//        setContentView(R.layout.activity_game);
//        rhythmGameView = (RhythmGameView) findViewById(R.id.RhythmGameView);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        rhythmGameView.init(metrics);

//        setContentView(R.layout.activity_game);
//        tetrisGameView = (TetrisGameView) findViewById(R.id.TetrisGameView);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        tetrisGameView.init(metrics);

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
