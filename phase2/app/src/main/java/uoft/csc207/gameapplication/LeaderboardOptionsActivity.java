package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;
import uoft.csc207.gameapplication.Utility.GameRequestService.GetUserService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LeaderboardOptionsActivity extends AppCompatActivity {

    private Button gameWrapperLeaderboardButton;
    private Button tetrisGameLeaderboardButton;
    private Button rhythmGameLeaderboardButton;
    private Button mazeGameLeaderboardButton;


    GetUserService getUserService;

    private int currentStage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_options);
        gameWrapperLeaderboardButton = (Button) findViewById(R.id.wrapper_leaderboard_button);
        tetrisGameLeaderboardButton = (Button) findViewById(R.id.tetris_scores_button);
        rhythmGameLeaderboardButton = (Button) findViewById(R.id.rhythm_scores_button);
        mazeGameLeaderboardButton = (Button) findViewById(R.id.maze_leaderboard_button);

        gameWrapperLeaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameWrapperLeaderboardActivity = new Intent(LeaderboardOptionsActivity.this, LeaderboardActivity.class);
                gameWrapperLeaderboardActivity.putExtra("leaderboardType", "WrapperGame");
                startActivity(gameWrapperLeaderboardActivity);
            }

        });

        tetrisGameLeaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tetrisGameLeaderboardActivity = new Intent(LeaderboardOptionsActivity.this, LeaderboardActivity.class);
                tetrisGameLeaderboardActivity.putExtra("leaderboardType", "TetrisGame");
                startActivity(tetrisGameLeaderboardActivity);

            }

        });

        rhythmGameLeaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rhythmGameLeaderboardActivity = new Intent(LeaderboardOptionsActivity.this, LeaderboardActivity.class);
                rhythmGameLeaderboardActivity.putExtra("leaderboardType", "RhythmGame");
                startActivity(rhythmGameLeaderboardActivity);

            }
        });

        mazeGameLeaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mazeGameLeaderboardActivity = new Intent(LeaderboardOptionsActivity.this, LeaderboardActivity.class);
                mazeGameLeaderboardActivity.putExtra("leaderboardType", "MazeGame");
                startActivity(mazeGameLeaderboardActivity);

            }
        });

//        getUserService = new GetUserService();
//        getUserService.setContext(this);
//
//        getUserService.getUser(LoginService.getLoginToken(), new CallBack() {
//            @Override
//            public void onSuccess() {
//                System.out.println(getUserService.getUser().getCurrentStage());
//                currentStage = Integer.valueOf(getUserService.getUser().getCurrentStage());
//                initialize();
//            }
//
//            @Override
//            public void onWait() {
//                System.out.println("waiting");
//            }
//
//            @Override
//            public void onFailure() {
//                System.out.println("failed to get user");
//            }
//        });
    }

}
