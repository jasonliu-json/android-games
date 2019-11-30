package uoft.csc207.gameapplication;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.GameWrapper.GameWrapperDriver;
import uoft.csc207.gameapplication.Games.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.RhythmGameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameDriver;
import uoft.csc207.gameapplication.Utility.JSONFileRW;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    GameView gameView;
    private GameDriver gameDriver;

    private TimerTask checkIsGameOver = new TimerTask() {
        @Override
        public void run() {
            if (gameDriver.getGameIsOver()) {
                finish();
            }
        }
    };

    // File reading and writing
    private static final String CUSTOMIZATIONS_FILE = "Customize.json";
    private JSONFileRW fileRW;
    private JSONObject configurations;
    private JSONObject tetrisCustom;
    private JSONObject rhythmCustom;
    private JSONObject mazeCustom;

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

        // Initialize from files, for state saving
        fileRW = new JSONFileRW(CUSTOMIZATIONS_FILE, this);
        loadJSON();

        switch (gameType) {
            case "gameWrapper":
//                System.out.println("playing game wrapper");
                gameDriver = new GameWrapperDriver();
                // gameDriver.init(metrics);
                gameDriver.setMetrics(metrics);
                gameDriver.setContext(this);
                gameDriver.setConfigurations("Tap:" + getRhythmSharedConfig() +
                        ";MISSED;4,100,Mii Channel,RANDOM:Swipe;AQUA");
//                gameDriver.init();
                gameView.setStage("1");
                break;
            case "tetrisGame":
//                System.out.println("playing tetris");
                gameDriver = new TetrisGameDriver();
                gameDriver.init(metrics);
                gameView.setStage("2");
                break;
            case "rhythmGame":
                gameDriver = new RhythmGameDriver();
                gameDriver.setMetrics(metrics);
                gameDriver.setContext(this);
                gameDriver.setConfigurations(getRhythmSharedConfig()+ ";STATS;3,100,Mii Channel,SONG;" +
                        "4,100,Old Town Road,SONG;4,80,THIRD SONG,SONG");
                gameDriver.init();
                gameView.setStage("3");
                break;
            case "mazeGame":
                System.out.println("playing maze");
                gameDriver = new MazeGameDriver(this);
                gameDriver.init(metrics);
                gameView.setStage("4");
                break;
            default:
                gameDriver = new TetrisGameDriver();
                gameDriver.init(metrics);
                break;
        }

        gameView.setDriver(gameDriver);
    }

    /**
     * Loads the configurations
     */
    private void loadJSON() {
        configurations = fileRW.load();
        if (configurations != null) {
            try {
                tetrisCustom = configurations.getJSONObject("tetris");
                rhythmCustom = configurations.getJSONObject("rhythm");
                mazeCustom = configurations.getJSONObject("maze");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String getRhythmSharedConfig() {
        StringBuilder configs = new StringBuilder("");
        try {
            configs.append(tetrisCustom.getString("colours"));
            configs.append(";");
            for (int i = 0; i < 4; i++)
                configs.append(rhythmCustom.getString(String.format(Locale.CANADA, "shape%d", i+1)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return configs.toString();
    }



//    /**
//     * Sets the state of the game, based on previous values.
//     */
//    private void setState() {
//        int gameState;
//        int savedScore;
//        try {
//            gameState = Integer.valueOf(userdata.getString("savedStage"));
//            savedScore = Integer.valueOf(userdata.getString(("savedPoints")));
////            gameDriver.setGameState(savedScore, gameState);
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Resumes the game.
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.start();
        timer = new Timer();
        timer.scheduleAtFixedRate(checkIsGameOver, 0, 100);
        gameSessionStart = System.currentTimeMillis();
    }

    /**
     * Pauses the game and writes changes.
     */
    @Override
    protected void onPause() {
        super.onPause();
//        System.out.println("onPause()");
        gameView.stop();

        timer.cancel();
        timer.purge();
    }

//    /**
//     * Updates score.
//     */
//    private void updateScores() {
//        Comparator<String> byValue = new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return Integer.parseInt(o2) - Integer.parseInt(o1);
//            }
//        };
//
//        try {
//            userdata.put("savedStage", "0");
//            userdata.put("savedPoints", "0");
//            JSONArray userScore = userdata.getJSONArray("topPlays");
//            String[] scores = new String[10];
//            for (int i = 0; i < userScore.length(); i ++) {
//                scores[i] = userScore.getJSONObject(i).getString(String.format("top%d", i));
//            }
//            if (Integer.valueOf(scores[9]) < gameDriver.getPoints()) {
////                System.out.println(gameDriver.getPoints());
//                scores[9] = String.valueOf(gameDriver.getPoints());
//            }
//            Arrays.sort(scores, byValue);
//            for (int i = 0; i < userScore.length(); i++) {
//                JSONObject scorePosition = userScore.getJSONObject(i);
//                scorePosition.put(String.format("top%d", i), scores[i]);
//            }
//            int points = Integer.valueOf(userdata.getString("totalPoints"));
//            points += gameDriver.getPoints();
//            userdata.put("totalPoints", String.valueOf(points));
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }



//    /**
//     * Updates time.
//     */
//    private void updateTimePlayed() {
//        try {
//            int timePlayed = Integer.valueOf(userdata.getString("timePlayed"));
//            timePlayed += ((System.currentTimeMillis() - gameSessionStart)/1000);
//            userdata.put("timePlayed", String.valueOf(timePlayed));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Stops the game and saves changes.
//     */
//    @Override
//    protected void onStop() {
//        super.onStop();
////        saveState();
////        fileRW.write(database.toString());
//        // hardcoded manual stop
//    }
//
//    /**
//     * Saves the state and the points.
//     */
//    private void saveState() {
//        try {
//            if (gameDriver.getGameIsOver()) {
//                userdata.put("savedStage", "0");
//                userdata.put("savedPoints", "0");
//            }
//            else {
////                userdata.put("savedStage", String.valueOf(gameDriver.getGameState()));
////                userdata.put("savedPoints", String.valueOf(gameDriver.getPoints()));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


}
