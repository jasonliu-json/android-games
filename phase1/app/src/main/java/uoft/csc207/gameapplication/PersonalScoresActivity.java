package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PersonalScoresActivity extends AppCompatActivity {

    ArrayList<String> personalScores = new ArrayList<>();
    String timePlayedText;
    String totalPointsText;

    private static final String FILE = "UserData.json";

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_scores);
        username = getIntent().getExtras().getString("username");
        loadPersonalScores();

//        personalScores.add("1");
//        personalScores.add("2");
//        personalScores.add("9000");


        String scores = "";
        for (int i=0; i<personalScores.size(); i++) {
            scores += personalScores.get(i) + " \n";
        }

        TextView title = (TextView) findViewById(R.id.score_title);
        title.setTextSize(30);
        title.setText("Personal Scores");

        TextView scoreList = (TextView) findViewById(R.id.scores);
        scoreList.setTextSize(20);
        scoreList.setText(scores);

        // hard coded for now
        timePlayedText = "100";
        totalPointsText = "9000";

        TextView timePlayed = (TextView) findViewById(R.id.time_played);
        timePlayed.setTextSize(20);
        timePlayedText = "Time Played: " + timePlayedText;
        timePlayed.setText(timePlayedText);

        TextView totalPoints = (TextView) findViewById(R.id.total_points);
        totalPoints.setTextSize(20);
        totalPointsText = "Time Played: " + totalPointsText;
        totalPoints.setText(totalPointsText);







    }

    private void loadPersonalScores() {
        JSONObject jsonObject;
        try {
            int i = 0;
            String jsonString = "";
            try {
                FileInputStream fileInputStream = openFileInput(FILE);
                while ((i = fileInputStream.read()) != -1) {
                    jsonString += (char) i;
                }
                fileInputStream.close();
            }
            catch (FileNotFoundException e) {
                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(getAssets().open(FILE)));
                while ((i = bufferReader.read()) != -1) {
                    jsonString += (char) i;
                }
                bufferReader.close();
            }
            jsonObject = new JSONObject(jsonString);
            loadUserScore(jsonObject);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserScore(JSONObject jsonObject) {
        try {
            JSONArray users = jsonObject.getJSONArray("users");
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    System.out.println(user.getString("username"));

                    JSONArray scores = user.getJSONArray("topPlays");

                    for (int s = 0; s < scores.length(); s++) {
                        personalScores.add(scores.getJSONObject(s).getString(String.format("top%d", s)));
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
