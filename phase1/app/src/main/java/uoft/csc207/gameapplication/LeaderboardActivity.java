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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class LeaderboardActivity extends AppCompatActivity {

    private String[] topTenPlayers = new String[10];
    private String[] topTenScores = new String[10];
//    private String leaderboard = "Player          Score \n";
    private static final String FILE = "UserData.json";
    private JSONArray database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        loadDatabase();
        String[][] topPlays = getTopPlayers();
        topTenPlayers = topPlays[1];
        topTenScores = topPlays[0];

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

    private void loadDatabase() {
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
            JSONObject jsonFile = new JSONObject(jsonString);
            database = jsonFile.getJSONArray("users");
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

    public String[][] getTopPlayers() {
        String[][] topScores = {{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"},
                                {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}};

        Comparator<String[]> byValue = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o2[0]) - Integer.parseInt(o1[0]);
            }
        };

        HashMap<String, String[]> scoreMapping = new HashMap();

        for (int i = 0; i < database.length(); i++) {
            try {
                JSONObject user = database.getJSONObject(i);
                String username = user.getString("username");
                JSONArray userScores = user.getJSONArray("topPlays");
                for (int s = 0; s < userScores.length(); s++) {
                    String score = userScores.getJSONObject(s).getString(String.format("top%d", s));
                    if (Integer.valueOf(score) > Integer.valueOf(topScores[9][0])) {
                        topScores[9][0] = score;
                        topScores[9][1] = username;
                        Arrays.sort(topScores, byValue);
                    }
                    else {
                        break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String[][] TopPlayers = new String[2][10];
        // 0 is scores
        // 1 is users
        for (int i = 0; i < topScores.length; i++) {
            System.out.println(i);
            TopPlayers[0][i] = topScores[i][0];
            TopPlayers[1][i] = topScores[i][1];
        }
        return TopPlayers;
    }
}

