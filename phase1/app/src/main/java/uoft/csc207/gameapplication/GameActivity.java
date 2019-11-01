package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import uoft.csc207.gameapplication.MazeGame.MazeGameView;
import uoft.csc207.gameapplication.RhythmGame.RhythmGameView;
import uoft.csc207.gameapplication.TetrisGame.TetrisGameView;

import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity {
    GameView gameView;

    private String username;
    private GameWrapperDriver gameWrapperDriver;
    private Timer timer;
    private static final String FILE = "UserData.json";
    private JSONObject database;
    private JSONObject userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intent returnToMenu = new Intent(GameActivity.this, Login.class);
        username = getIntent().getExtras().getString("username");
        // implement feature to pass to user to enter password
        setContentView(R.layout.activity_game);
        gameView = (GameView) findViewById(R.id.GameView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gameView.init(metrics);

        gameWrapperDriver = gameView.getGameWrapperDriver();

        loadJson();
        setState();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                saveState();
                if (gameWrapperDriver.getGameIsOver()) { // should be the condition that the game is over;
                    timer.cancel();
                    timer.purge();
                    updateScores();
                    writeState();
                    Intent intent = new Intent(GameActivity.this, MainMenuActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        }, 0, 100);
    }

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
                System.out.println(gameWrapperDriver.getPoints());
                scores[9] = String.valueOf(gameWrapperDriver.getPoints());
            }
            Arrays.sort(scores, byValue);
            for (int i = 0; i < userScore.length(); i++) {
                JSONObject scorePosition = userScore.getJSONObject(i);
                scorePosition.put(String.format("top%d", i), scores[i]);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void writeState() {
        String jsonText = database.toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE, MODE_PRIVATE);
            fileOutputStream.write(jsonText.getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveState() {
        try {
            userdata.put("savedStage", String.valueOf(gameWrapperDriver.getGameState()));
            userdata.put("savedPoints", String.valueOf(gameWrapperDriver.getPoints()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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

    private void loadJson() {
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
            database = new JSONObject(jsonString);
            JSONArray users = database.getJSONArray("users");
            for (int s = 0; s < users.length(); s++) {
                JSONObject user = users.getJSONObject(s);
                if (user.getString("username").equals(username)) {
                    userdata = user;
                }
            }
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
}
