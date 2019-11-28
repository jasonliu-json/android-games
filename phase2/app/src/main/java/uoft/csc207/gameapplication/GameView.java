package uoft.csc207.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Utility.GameRequestService.CallBack;
import uoft.csc207.gameapplication.Utility.GameRequestService.LoginService;
import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Score;
import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Token;
import uoft.csc207.gameapplication.Utility.GameRequestService.ScorePosterService;
import uoft.csc207.gameapplication.Utility.GameRequestService.StagePosterService;


public class GameView extends View {
    private GameDriver gameDriver;
    private Timer timer;
    private boolean scorePosted = false;
    private boolean stageUpdated = false;
    private String stage = "0";

    private ScorePosterService scorePosterService;
    private StagePosterService stagePosterService;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scorePosterService = new ScorePosterService();
        scorePosterService.setContext(context);
        stagePosterService = new StagePosterService();
        stagePosterService.setContext(context);
    }

    public void start() {
//        gameDriver.start()
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameDriver.getGameIsOver()) { // should be the condition that the game is over;
                    stop();
                } else {
//                    gameDriver.update();
                    invalidate();
                }
            }
        }, 0, 30);
    }

    public void stop() {
//        gameDriver.stop()
        timer.cancel();
        timer.purge();

        postScores();
        postStage();
    }

    private void postScores() {
        if (!scorePosted) {
            Token token = LoginService.getLoginToken();

            Score score = new Score();
            score.setScore(String.valueOf(gameDriver.getPoints()));
            score.setUsername(token.getUsername());

            scorePosterService.postScore(token, score, "WrapperGame", new CallBack() {
                @Override
                public void onSuccess() {
                    System.out.println("successful Request");
                }

                @Override
                public void onFailure() {
                    System.out.println("Failed Request");
                }

                @Override
                public void onWait() {
                    System.out.println("Waiting");
                }
            });
            scorePosted = true;
        }
    }

    private void postStage() {
        if (!stageUpdated) {
            Token token = LoginService.getLoginToken();

            stagePosterService.postStage(token, stage, new CallBack() {
                @Override
                public void onSuccess() {
                    System.out.println("successful Request");
                }

                @Override
                public void onFailure() {
                    System.out.println("Failed Request");
                }

                @Override
                public void onWait() {
                    System.out.println("Waiting");
                }
            });
            stageUpdated = true;
        }
    }
//
//    public void init(DisplayMetrics metrics) {
//        gameDriver.init(metrics);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        gameDriver.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                gameDriver.touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                gameDriver.touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP :
                gameDriver.touchUp();
                break;
        }

        return true;
    }

    public void setDriver(GameDriver driver) {
        this.gameDriver = driver;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}










