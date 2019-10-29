package uoft.csc207.gameapplication.TetrisGame;

import uoft.csc207.gameapplication.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TetrisMainActivity extends MainActivity {


    private TetrisGameDriver tetrisGameDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//                    if (!tetrisGameDriver.getPiece().hit()){
//                        if (!tetrisGameDriver.getBoard().gameOver()){
//                            tetrisGameDriver.getBoard().addPiece();
//                    }
//                }
            }

        }

    };

private class MyTask extends TimerTask {
    @Override
    public void run() {
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }

}

    @Override
    protected void onPause() {
        super.onPause();
    }
}
