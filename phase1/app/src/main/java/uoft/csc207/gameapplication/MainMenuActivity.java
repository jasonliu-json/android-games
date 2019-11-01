package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    private Button playButton;
    private Button leaderboardButton;
    private Button scoreButton;

    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        playButton = (Button) findViewById(R.id.play_button);
        leaderboardButton = (Button) findViewById(R.id.leaderboard_button);
        scoreButton = (Button) findViewById(R.id.score_button);
        username = getIntent().getExtras().getString("username");

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivity = new Intent(MainMenuActivity.this, GameActivity.class);
                gameActivity.putExtra("username", username);
                startActivity(gameActivity);
            }

        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboardActivity = new Intent(MainMenuActivity.this, LeaderboardActivity.class);
                startActivity(leaderboardActivity);
            }

        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent personalScoresActivity = new Intent(MainMenuActivity.this, PersonalScoresActivity.class);
                personalScoresActivity.putExtra("username", username);
                startActivity(personalScoresActivity);
            }
        });
    }


//
//        registerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent registerUser = new Intent(Login.this, RegisterUser.class);
//                startActivity(registerUser);
//            }
//        });
//    }


}
