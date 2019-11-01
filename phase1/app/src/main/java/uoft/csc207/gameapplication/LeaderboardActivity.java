package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;

public class LeaderboardActivity extends AppCompatActivity {

    private String[] topTenPlayers = new String[10];
    private String[] topTenScores = new String[10];
    private String leaderboard = "Player          Score \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        topTenPlayers = new String[] {"p1", "p2", "p3", "p4", "p5", "p6", "p7787", "p888", "p9", "p10"};
        topTenScores = new String[] {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
        for (int i=0; i<10; i++) {
            String row = topTenPlayers[i];
            while (row.length() < 16) {
                row += " ";
            }

            while (row.length() < 20 - topTenScores[i].length()) {
                row += " ";
            }
            row += topTenScores[i];
            leaderboard += row + " \n";
        }

        TextView textView = (TextView) findViewById(R.id.leaderboard_table);
        textView.setTextSize(20);
        textView.setText(leaderboard);
        // max characters in line is weird, like 7

//    System.out.println(leaderboard);
    }
}

