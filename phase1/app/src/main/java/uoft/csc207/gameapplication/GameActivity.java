package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.MazeGame.MazeGameView;

public class GameActivity extends AppCompatActivity {

    private MazeGameView mazeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mazeView = (MazeGameView) findViewById(R.id.MazeGameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mazeView.init(metrics);
    }
}
