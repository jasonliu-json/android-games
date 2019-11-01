package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;

public class LeaderboardActivity extends AppCompatActivity {

    private String[] topTenPlayers = new String[10];
    private String[] topTenScores = new String[10];
//    private String leaderboard = "Player          Score \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        topTenPlayers = new String[] {"p1", "p2", "p3", "p4", "p5", "p6", "p7787", "p888", "p9", "p10"};
        topTenScores = new String[] {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};

        int[] playerTextViewIds = {R.id.player1, R.id.player2, R.id.player3, R.id.player4,
                R.id.player5, R.id.player6, R.id.player7, R.id.player8, R.id.player9,
                R.id.player10};
        int[] scoreTextViewIds = {R.id.score1, R.id.score2, R.id.score3, R.id.score4, R.id.score5,
                R.id.score6, R.id.score7, R.id.score8, R.id.score9, R.id.score10};

        for (int i=0; i<10; i++) {
            TextView playerTextView = (TextView) findViewById(playerTextViewIds[i]);
            playerTextView.setTextSize(20);
            playerTextView.setText(topTenPlayers[i]);

            TextView scoreTextView = (TextView) findViewById(scoreTextViewIds[i]);
            scoreTextView.setTextSize(20);
            scoreTextView.setText(topTenScores[i]);
//            String row = topTenPlayers[i];
//            while (row.length() < 16) {
//                row += " ";
//            }
//
//            while (row.length() < 20 - topTenScores[i].length()) {
//                row += " ";
//            }
//            row += topTenScores[i];
//            leaderboard += row + " \n";
        }


//    System.out.println(leaderboard);
    }
}

