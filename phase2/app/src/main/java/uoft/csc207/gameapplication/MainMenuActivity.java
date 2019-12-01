package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Set up the main menu, creating four options for the users to choose by buttons.
 */
public class MainMenuActivity extends AppCompatActivity {

    private Button playButton;
    private Button leaderboardButton;
    private Button scoreButton;
    private Button customizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        playButton = (Button) findViewById(R.id.play_button);
        leaderboardButton = (Button) findViewById(R.id.leaderboard_button);
        scoreButton = (Button) findViewById(R.id.score_button);
        customizeButton = (Button) findViewById(R.id.customize_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gameOptionsActivity = new Intent(MainMenuActivity.this, GameOptionsActivity.class);
                startActivity(gameOptionsActivity);
//                Intent gameActivity = new Intent(MainMenuActivity.this, GameActivity.class);
//                startActivity(gameActivity);
            }

        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboardOptionsActivity = new Intent(MainMenuActivity.this, LeaderboardOptionsActivity.class);
                startActivity(leaderboardOptionsActivity);
            }

        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent personalScoresOptionsActivity = new Intent(MainMenuActivity.this, PersonalScoresOptionsActivity.class);
                startActivity(personalScoresOptionsActivity);
            }
        });

        customizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customizeActivity = new Intent(MainMenuActivity.this, CustomizeActivity.class);
                startActivity(customizeActivity);
            }
        });
    }


//
//        registerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent registerUser = new Intent(LoginActivity.this, RegisterUserActivity.class);
//                startActivity(registerUser);
//            }
//        });
//    }


}
