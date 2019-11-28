package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOptionsActivity extends AppCompatActivity {

    private Button gameWrapperButton;
    private Button tetrisGameButton;
    private Button rhythmGameButton;
    private Button mazeGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);
        gameWrapperButton = (Button) findViewById(R.id.game_wrapper);
        tetrisGameButton = (Button) findViewById(R.id.tetris_game);
        rhythmGameButton = (Button) findViewById(R.id.rhythm_game);
        mazeGameButton = (Button) findViewById(R.id.maze_game);

        gameWrapperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameWrapperActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                gameWrapperActivity.putExtra("gameType", "gameWrapper");
                startActivity(gameWrapperActivity);
            }

        });

        tetrisGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tetrisGameActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                tetrisGameActivity.putExtra("gameType", "tetrisGame");
                startActivity(tetrisGameActivity);
            }

        });

        rhythmGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rhythmGameActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                rhythmGameActivity.putExtra("gameType", "rhythmGame");
                startActivity(rhythmGameActivity);
            }
        });

        mazeGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mazeGameActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                mazeGameActivity.putExtra("gameType", "mazeGame");
                startActivity(mazeGameActivity);
            }
        });
    }
}
