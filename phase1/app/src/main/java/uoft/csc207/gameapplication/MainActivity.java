package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;
import uoft.csc207.gameapplication.RhythmGame.RhythmGame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {
            @Override
            public void run() {
            try {
                int refreshTime = RhythmGame.getRefreshTime();
                sleep(500);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(loginIntent);
            }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}