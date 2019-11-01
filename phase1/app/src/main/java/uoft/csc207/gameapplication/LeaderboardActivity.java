package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class LeaderboardActivity extends AppCompatActivity {

    private String[] topTenPlayers = new String[10];
    private String[] topTenScores = new String[10];
    private TextView[] rows = new TextView[10];
    private TextView titleRow;

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        topTenPlayers = new String[] {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10"};
        topTenScores = new String[] {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};

        titleRow = findViewById(R.id.leaderboard_title);

        rows[0] = (TextView) findViewById(R.id.row1);
        rows[1] = (TextView) findViewById(R.id.row2);
        rows[2] = (TextView) findViewById(R.id.row3);
        rows[3] = (TextView) findViewById(R.id.row4);
        rows[4] = (TextView) findViewById(R.id.row5);
        rows[5] = (TextView) findViewById(R.id.row6);
        rows[6] = (TextView) findViewById(R.id.row7);
        rows[7] = (TextView) findViewById(R.id.row8);
        rows[8] = (TextView) findViewById(R.id.row9);
        rows[9] = (TextView) findViewById(R.id.row10);

        titleRow.setText("Player          Score");
        for (int i=0; i<10; i++) {

            String player = String.format("%1$"+ (-15) +"s", topTenPlayers[i]).replace(' ', '_');
            String score = String.format("%1$"+5+"s", topTenScores[i]).replace(' ', '_');
            String row = player + score;
            rows[i].setText(row);
//      System.out.println(row);
        }

    }
}

