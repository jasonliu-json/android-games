package uoft.csc207.gameapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.GameWrapper.GameWrapperDriver;
import uoft.csc207.gameapplication.Games.MazeGame.MazeGameDriver;
import uoft.csc207.gameapplication.Games.RhythmGame.RhythmGameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.TetrisGameDriver;
import uoft.csc207.gameapplication.Utility.JSONFileRW;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
    private static final String COLOUR_PALETTES_FILE = "ColourPalettes.json";

    private JSONObject tetrisConfigurations;
    private JSONObject rhythmConfigurations;
    private JSONObject mazeConfigurations;
    private JSONObject allConfigurations;

    private Map<String, Integer> colourScheme;

    private Timer timer = new Timer();

    /**
     * Initializes the game on create.
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadCustomizeJSON();
        loadColourPaletteJSON();

        // Determines which game to play
        String gameType = getIntent().getStringExtra("gameType");
        switch (gameType) {
            case "gameWrapper":
                gameDriver = new GameWrapperDriver();
                gameDriver.setConfigurations(allConfigurations);
                gameView.setStage("1");
                break;
            case "tetrisGame":
                gameDriver = new TetrisGameDriver();
                gameDriver.setConfigurations(tetrisConfigurations);
                gameView.setStage("2");
                break;
            case "rhythmGame":
                gameDriver = new RhythmGameDriver();
                gameDriver.setConfigurations(rhythmConfigurations);
                gameView.setStage("3");
                break;
            case "mazeGame":
                gameDriver = new MazeGameDriver(this);
                gameDriver.setConfigurations(mazeConfigurations);
                gameView.setStage("4");
                break;
            default:
                gameDriver = new GameWrapperDriver();
                gameDriver.setConfigurations(tetrisConfigurations);
                break;
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gameDriver.setMetrics(metrics);
        gameDriver.setContext(this);
        gameDriver.setColourScheme(colourScheme);
        gameDriver.init();

        gameView = findViewById(R.id.GameView);
        gameView.setDriver(gameDriver);
    }

    /**
     * Loads the configurations
     */
    private void loadCustomizeJSON() {
        JSONFileRW customizeFileRW = new JSONFileRW(CUSTOMIZATIONS_FILE, this);
        allConfigurations = customizeFileRW.load();

        if (allConfigurations != null) {
            try {
                tetrisConfigurations = allConfigurations.getJSONObject("tetris");
                rhythmConfigurations = allConfigurations.getJSONObject("rhythm");
                mazeConfigurations = allConfigurations.getJSONObject("maze");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the colour scheme based on the selected theme.
     */
    private void loadColourPaletteJSON() {
        JSONFileRW colourFileRW = new JSONFileRW(COLOUR_PALETTES_FILE, this);
        JSONObject palettes = colourFileRW.load();
        if (palettes != null) {
            try {
                // Gets the theme and parses it into a map.
                String theme = tetrisConfigurations.getString("colours");
                JSONObject colourPalette = palettes.getJSONObject(theme);
                Map<String, Integer> colourScheme = new HashMap<>();
                for (int i = 1; i <= 7; i++) {
                    String key = String.format(Locale.CANADA, "BlockColour%d", i);
                    JSONObject colourRGB = colourPalette.getJSONObject(key);
                    Integer colour = Color.rgb(colourRGB.getInt("r"),
                            colourRGB.getInt("g"), colourRGB.getInt("b"));
                    colourScheme.put(key, colour);
                }

                this.colourScheme = Collections.unmodifiableMap(colourScheme);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


//    private String getRhythmSharedConfig() {
//        StringBuilder configs = new StringBuilder();
//        try {
//            configs.append(tetrisConfigurations.getString("colours"));
//            configs.append(";");
//            for (int i = 0; i < 4; i++)
//                configs.append(rhythmConfigurations.getString(String.format(Locale.CANADA, "shape%d", i+1)));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return configs.toString();
//    }
//
//    private String getTetrisConfig() {
//        StringBuilder configs = new StringBuilder("");
//        try {
//            configs.append(tetrisConfigurations.getString("colours").toLowerCase());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return configs.toString();
//    }

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
        timer.scheduleAtFixedRate(checkIsGameOver, 0, 100);
//        gameSessionStart = System.currentTimeMillis();
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
////        customizeFileRW.write(database.toString());
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
