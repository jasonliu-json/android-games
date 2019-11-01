package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonalScoresActivity extends AppCompatActivity {

    ArrayList<String> personalScores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_scores);

        personalScores.add("1");
        personalScores.add("2");
        personalScores.add("9000");


        String scores = "";
        for (int i=0; i<personalScores.size(); i++) {
            scores += personalScores.get(i) + " \n";
        }
        TextView textView = (TextView) findViewById(R.id.scores);
        textView.setTextSize(20);
        textView.setText(scores);
    }
}
