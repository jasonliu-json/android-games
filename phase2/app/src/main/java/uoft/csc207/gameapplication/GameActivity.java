package uoft.csc207.gameapplication;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.GameWrapper.GameWrapperDriver;
import uoft.csc207.gameapplication.Games.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameDriver;
import uoft.csc207.gameapplication.Utility.JSONFileRW;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    GameView gameView;
    private GameDriver gameDriver;

    // File reading and writing
    private String username;
    private static final String FILE = "UserData.json";
    private JSONFileRW fileRW;
    private JSONObject database;
    private JSONObject userdata;

    private long gameSessionStart;
    private Timer timer;
    private String gameType = "gameWrapper";



    /**
     * Initializes the game on create.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check game type

        gameType = getIntent().getStringExtra("gameType");

        // Initialize view and dimension metrics
        setContentView(R.layout.activity_game);
        gameView = (GameView) findViewById(R.id.GameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        System.out.println(gameType);

//        if (gameType.equals("tetrisGame")) {
//            gameDriver = new TetrisGameDriver(this);
//        } else if (gameType.equals("rhythmGame")) {
////                gameDriver = new RhythmGameDriver(metrics, this, configs??);
//        } else if (gameType.equals("mazeGame")) {
//            gameDriver = new MazeGameDriver(this);
//        } else {
//            gameDriver = new GameWrapperDriver(metrics, this);
//        }

        switch (gameType) {
            case "gameWrapper":
                System.out.println("playing game wrapper");
                gameDriver = new GameWrapperDriver(metrics, this);
                gameView.setStage("1");
                break;

            case "tetrisGame":
                System.out.println("playing tetris");
                gameDriver = new TetrisGameDriver(this);
                gameDriver.init(metrics);
                gameView.setStage("2");
                break;

            case "rhythmGame":
//                gameDriver = new RhythmGameDriver(metrics, this, configs??);
                gameView.setStage("3");
                break;

            case "mazeGame":
                System.out.println("playing maze");
                gameDriver = new MazeGameDriver(this);
                gameDriver.init(metrics);
                gameView.setStage("4");
                break;

            default:
                gameDriver = new GameWrapperDriver(metrics, this);
                break;
        }

        gameView.setDriver(gameDriver);
        gameView.start();

        // Initialize from files, for state saving
//        fileRW = new JSONFileRW(FILE, this);
    }

    protected void onStart() {
        super.onStart();
//        loadJSON();
//        setState();
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
//            gameDriver.setGameState(savedScore, gameState);
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
//                saveState();
                if (gameDriver.getGameIsOver()) { // should be the condition that the game is over;
//                    updateScores();

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
            if (Integer.valueOf(scores[9]) < gameDriver.getPoints()) {
//                System.out.println(gameDriver.getPoints());
                scores[9] = String.valueOf(gameDriver.getPoints());
            }
            Arrays.sort(scores, byValue);
            for (int i = 0; i < userScore.length(); i++) {
                JSONObject scorePosition = userScore.getJSONObject(i);
                scorePosition.put(String.format("top%d", i), scores[i]);
            }
            int points = Integer.valueOf(userdata.getString("totalPoints"));
            points += gameDriver.getPoints();
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
//        updateTimePlayed();
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
//        saveState();
//        fileRW.write(database.toString());
        // hardcoded manual stop
    }

    /**
     * Saves the state and the points.
     */
    private void saveState() {
        try {
            if (gameDriver.getGameIsOver()) {
                userdata.put("savedStage", "0");
                userdata.put("savedPoints", "0");
            }
            else {
//                userdata.put("savedStage", String.valueOf(gameDriver.getGameState()));
//                userdata.put("savedPoints", String.valueOf(gameDriver.getPoints()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
