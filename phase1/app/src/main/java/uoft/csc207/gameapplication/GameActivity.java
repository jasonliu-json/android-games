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
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intent returnToMenu = new Intent(GameActivity.this, Login.class);
        setContentView(R.layout.activity_game);
        gameView = (GameView) findViewById(R.id.GameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gameView.init(metrics);
    }
}
