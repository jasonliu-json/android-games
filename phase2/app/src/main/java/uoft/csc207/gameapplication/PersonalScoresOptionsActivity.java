package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalScoresOptionsActivity extends AppCompatActivity {

    private Button gameWrapperScoresButton;
    private Button tetrisGameScoresButton;
    private Button rhythmGameScoresButton;
    private Button mazeGameScoresButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_scores_options);

        gameWrapperScoresButton = (Button) findViewById(R.id.wrapper_scores_button);
        tetrisGameScoresButton = (Button) findViewById(R.id.tetris_scores_button);
        rhythmGameScoresButton = (Button) findViewById(R.id.rhythm_scores_button);
        mazeGameScoresButton = (Button) findViewById(R.id.maze_scores_button);

        gameWrapperScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameWrapperScoresActivity = new Intent(PersonalScoresOptionsActivity.this, PersonalScoresActivity.class);
                gameWrapperScoresActivity.putExtra("scoresType", "WrapperGame");
                startActivity(gameWrapperScoresActivity);
            }

        });

        tetrisGameScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tetrisGameScoresActivity = new Intent(PersonalScoresOptionsActivity.this, PersonalScoresActivity.class);
                tetrisGameScoresActivity.putExtra("scoresType", "TetrisGame");
                startActivity(tetrisGameScoresActivity);

            }

        });

        rhythmGameScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rhythmGameScoresActivity = new Intent(PersonalScoresOptionsActivity.this, PersonalScoresActivity.class);
                rhythmGameScoresActivity.putExtra("scoresType", "RhythmGame");
                startActivity(rhythmGameScoresActivity);

            }
        });

        mazeGameScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mazeGameScoresActivity = new Intent(PersonalScoresOptionsActivity.this, PersonalScoresActivity.class);
                mazeGameScoresActivity.putExtra("scoresType", "MazeGame");
                startActivity(mazeGameScoresActivity);

            }
        });
    }
}
