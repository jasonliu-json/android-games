package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uoft.csc207.gameapplication.Utility.GameRequestService.CallBack;
import uoft.csc207.gameapplication.Utility.GameRequestService.GetUserService;
import uoft.csc207.gameapplication.Utility.GameRequestService.LoginService;
import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Score;
import uoft.csc207.gameapplication.Utility.GameRequestService.Models.User;

public class PersonalScoresActivity extends AppCompatActivity {

    ArrayList<String> personalScores = new ArrayList<>();
    String timePlayedText;
    String totalPointsText;
//
//    private static final String FILE = "UserData.json";
//
//    private String username;
//    private JSONObject jsonUserObject;

    GetUserService getUserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_scores);

        getUserService = new GetUserService();
        getUserService.setContext(this);

        getUserService.getUser(LoginService.getLoginToken(), new CallBack() {
            @Override
            public void onSuccess() {
                initialize(getUserService.getUser());
            }

            @Override
            public void onWait() {
                System.out.println("waiting");
            }

            @Override
            public void onFailure() {
                System.out.println("failed to get user");
            }
        });
    }

    private void initialize(User user) {
        // creates string representation of total point text
        totalPointsText = user.getTotalPoints();
        totalPointsText = "Total Points: \n" + totalPointsText;

        // creates string representation of total time played text
        timePlayedText = user.getTimePlayed();

        int minutesPlayed = Integer.valueOf(timePlayedText) / 60;
        int extraSeconds = Integer.valueOf(timePlayedText) % 60;
        timePlayedText = "Time Played: \n" + minutesPlayed + "m " + extraSeconds + "s";

        // creates the string representation of user score board
        List<Score> scores = user.getUserScores().get(0).getScores();

        String scoreBoard = "";
        for (int i=0; i<scores.size(); i++) {
            scoreBoard += scores.get(i).getScore() + " \n";
        }

        // begin settings those strings.
        TextView title = (TextView) findViewById(R.id.score_title);
        title.setTextSize(30);
        title.setText("Personal Scores");

        TextView scoreList = (TextView) findViewById(R.id.scores);
        scoreList.setTextSize(20);
        scoreList.setText(scoreBoard);



        TextView timePlayed = (TextView) findViewById(R.id.time_played);
        timePlayed.setTextSize(20);
        timePlayed.setText(timePlayedText);

        TextView totalPoints = (TextView) findViewById(R.id.total_points);
        totalPoints.setTextSize(20);
        totalPoints.setText(totalPointsText);
    }

//    /**
//     * gets the time played by this user
//     * @return the total time played by this user
//     */
//    private String getTimePlayed() {
//        try {
//            return jsonUserObject.getString("timePlayed");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * return the total amount of points accumulated by this user
//     * @return the users total points scored in the lifetime
//     */
//    private String getTotalPoints() {
//        try {
//            return jsonUserObject.getString("totalPoints");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * Creates the jsonObject by reading the data base off assets or memory and loads the
//     * players top 10 scores
//     */
//    private void loadPersonalScores() {
//        JSONObject jsonObject;
//        try {
//            int i = 0;
//            String jsonString = "";
//            try {
//                FileInputStream fileInputStream = openFileInput(FILE);
//                while ((i = fileInputStream.read()) != -1) {
//                    jsonString += (char) i;
//                }
//                fileInputStream.close();
//            }
//            catch (FileNotFoundException e) {
//                BufferedReader bufferReader = new BufferedReader(
//                        new InputStreamReader(getAssets().open(FILE)));
//                while ((i = bufferReader.read()) != -1) {
//                    jsonString += (char) i;
//                }
//                bufferReader.close();
//            }
//            jsonObject = new JSONObject(jsonString);
//            loadUserScore(jsonObject);
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * helper that loads up this current users top 10 best scores
//     * @param jsonObject the json data base for the users
//     */
//    private void loadUserScore(JSONObject jsonObject) {
//        try {
//            JSONArray users = jsonObject.getJSONArray("users");
//            for (int i = 0; i < users.length(); i++) {
//                JSONObject user = users.getJSONObject(i);
//                if (user.getString("username").equals(username)) {
//                    jsonUserObject = user;
//
//                    JSONArray scores = user.getJSONArray("topPlays");
//
//                    for (int s = 0; s < scores.length(); s++) {
//                        personalScores.add(scores.getJSONObject(s).getString(String.format("top%d", s)));
//                    }
//                }
//            }
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
