package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;

public class LeaderboardActivity extends AppCompatActivity {

    private String[] topTenPlayers = new String[10];
    private String[] topTenScores = new String[10];
    private String leaderboard = "Player === Score \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        topTenPlayers = new String[] {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10"};
        topTenScores = new String[] {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
        for (int i=0; i<10; i++) {
            leaderboard += topTenPlayers[i] + " === " + topTenScores[i] + " \n";
        }

        TextView textView = (TextView) findViewById(R.id.leaderboard_table);
        textView.setText(leaderboard);

    }
}
