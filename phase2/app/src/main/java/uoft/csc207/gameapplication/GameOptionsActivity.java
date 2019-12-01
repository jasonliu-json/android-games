package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uoft.csc207.gameapplication.Utility.GameRequestService.CallBack;
import uoft.csc207.gameapplication.Utility.GameRequestService.GetUserService;
import uoft.csc207.gameapplication.Utility.GameRequestService.LoginService;
import uoft.csc207.gameapplication.Utility.JSONFileRW;

public class GameOptionsActivity extends AppCompatActivity {

    private Button gameWrapperButton;
    private Button tetrisGameButton;
    private Button rhythmGameButton;
    private Button mazeGameButton;


    GetUserService getUserService;

    private int currentStage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        getUserService = new GetUserService();
        getUserService.setContext(this);

        getUserService.getUser(LoginService.getLoginToken(), new CallBack() {
            @Override
            public void onSuccess() {
                System.out.println(getUserService.getUser().getCurrentStage());
                currentStage = Integer.valueOf(getUserService.getUser().getCurrentStage());
                initialize();
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

    private void initialize() {
        gameWrapperButton = (Button) findViewById(R.id.game_wrapper);
        tetrisGameButton = (Button) findViewById(R.id.tetris_game);
        rhythmGameButton = (Button) findViewById(R.id.rhythm_game);
        mazeGameButton = (Button) findViewById(R.id.maze_game);

        gameWrapperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameWrapperActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                gameWrapperActivity.putExtra("gameType", "gameWrapper");
                startActivity(gameWrapperActivity);
                finish();
            }

        });

        tetrisGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStage > 0) {
                    Intent tetrisGameActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                    tetrisGameActivity.putExtra("gameType", "tetrisGame");
                    startActivity(tetrisGameActivity);
                    finish();
                }
            }

        });

        rhythmGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStage > 1) {
                    configureRhythmLevels();
                    Intent rhythmGameActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                    rhythmGameActivity.putExtra("gameType", "rhythmGame");
                    startActivity(rhythmGameActivity);
                    finish();
                }
            }
        });

        mazeGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStage > 2) {
                    Intent mazeGameActivity = new Intent(GameOptionsActivity.this, GameActivity.class);
                    mazeGameActivity.putExtra("gameType", "mazeGame");
                    startActivity(mazeGameActivity);
                    finish();
                }
            }
        });
    }

    private void configureRhythmLevels() {
        JSONFileRW fileRW = new JSONFileRW("Customize.json", this);
        try {
            JSONObject allCust = fileRW.load();
            JSONObject rhythmCust = allCust.getJSONObject("rhythm");
            rhythmCust.put("presenterMode", "MISSED");
            JSONArray levelsArray = new JSONArray("[{\"numColumns\": 4,\n" +
                    "          \"height\": 100,\n" +
                    "          \"song\": \"Old Town Road\",\n" +
                    "          \"mode\": \"RANDOM\"}]");
            rhythmCust.put("levels", levelsArray);
            fileRW.write(allCust.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
