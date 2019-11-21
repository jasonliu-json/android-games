package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    GameView gameView;
    private GameWrapperDriver gameWrapperDriver;

    // File reading and writing
    private String username;
    private static final String FILE = "UserData.json";
    private JSONFileRW fileRW;
    private JSONObject database;
    private JSONObject userdata;

    private long gameSessionStart;
    private Timer timer;

    /**
     * Initializes the game on create.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getExtras().getString("username");

        // Initialize view and dimension metrics
        setContentView(R.layout.activity_game);
        gameView = (GameView) findViewById(R.id.GameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gameView.init(metrics);

        gameWrapperDriver = gameView.getGameWrapperDriver();

        // Initialize from files, for state saving
        fileRW = new JSONFileRW(FILE, this);
    }

    protected void onStart() {
        super.onStart();
        loadJSON();
        setState();
    }

    /**
     * Loads the user's data.
     */
    private void loadJSON() {
        database = fileRW.load();
        if (database != null) {
            try {
                JSONArray users = database.getJSONArray("users");
                for (int s = 0; s < users.length(); s++) {
                    JSONObject user = users.getJSONObject(s);
                    if (user.getString("username").equals(username)) {
                        userdata = user;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the state of the game, based on previous values.
     */
    private void setState() {
        int gameState;
        int savedScore;
        try {
            gameState = Integer.valueOf(userdata.getString("savedStage"));
            savedScore = Integer.valueOf(userdata.getString(("savedPoints")));
            gameWrapperDriver.setGameState(savedScore, gameState);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resumes the game.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // At regular intervals, check if the game is over and save the state of the game
        gameView.start();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                saveState();
                if (gameWrapperDriver.getGameIsOver()) { // should be the condition that the game is over;
                    updateScores();

                    // closes this activity
                    finish();
                }
            }
        }, 0, 100);

        gameSessionStart = System.currentTimeMillis();
    }

    /**
     * Updates score.
     */
    private void updateScores() {
        Comparator<String> byValue = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o2) - Integer.parseInt(o1);
            }
        };

        try {
            userdata.put("savedStage", "0");
            userdata.put("savedPoints", "0");
            JSONArray userScore = userdata.getJSONArray("topPlays");
            String[] scores = new String[10];
            for (int i = 0; i < userScore.length(); i ++) {
                scores[i] = userScore.getJSONObject(i).getString(String.format("top%d", i));
            }
            if (Integer.valueOf(scores[9]) < gameWrapperDriver.getPoints()) {
//                System.out.println(gameWrapperDriver.getPoints());
                scores[9] = String.valueOf(gameWrapperDriver.getPoints());
            }
            Arrays.sort(scores, byValue);
            for (int i = 0; i < userScore.length(); i++) {
                JSONObject scorePosition = userScore.getJSONObject(i);
                scorePosition.put(String.format("top%d", i), scores[i]);
            }
            int points = Integer.valueOf(userdata.getString("totalPoints"));
            points += gameWrapperDriver.getPoints();
            userdata.put("totalPoints", String.valueOf(points));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the game and writes changes.
     */
    @Override
    protected void onPause() {
        super.onPause();
//        System.out.println("onPause()");
        updateTimePlayed();
        gameView.stop();

//        writeState();
        timer.cancel();
        timer.purge();
    }

    /**
     * Updates time.
     */
    private void updateTimePlayed() {
        try {
            int timePlayed = Integer.valueOf(userdata.getString("timePlayed"));
            timePlayed += ((System.currentTimeMillis() - gameSessionStart)/1000);
            userdata.put("timePlayed", String.valueOf(timePlayed));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the game and saves changes.
     */
    @Override
    protected void onStop() {
        super.onStop();
        saveState();
        fileRW.write(database.toString());
        // hardcoded manual stop
    }

    /**
     * Saves the state and the points.
     */
    private void saveState() {
        try {
            if (gameWrapperDriver.getGameIsOver()) {
                userdata.put("savedStage", "0");
                userdata.put("savedPoints", "0");
            }
            else {
                userdata.put("savedStage", String.valueOf(gameWrapperDriver.getGameState()));
                userdata.put("savedPoints", String.valueOf(gameWrapperDriver.getPoints()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
