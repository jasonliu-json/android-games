package uoft.csc207.gameapplication.Games.TetrisGame;

import android.graphics.Canvas;
import android.util.DisplayMetrics;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.Controller.TetrisGameController;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.PieceGenerator;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.TetrisGame;
import uoft.csc207.gameapplication.Games.TetrisGame.Presenter.TetrisGamePresenter;

public class TetrisGameDriver extends GameDriver {

    private TetrisGameMediator mediator;

    public void init(DisplayMetrics metrics) {
        mediator = new TetrisGameMediator();
        mediator.setGame(new TetrisGame(new Board(10, 20), new PieceGenerator()));
        mediator.setPresenter(new TetrisGamePresenter(mediator));
        mediator.setController(new TetrisGameController(mediator, metrics));
    }

    public boolean getGameIsOver() {
        return mediator.getGameIsOver();
    }

    public int getPoints() {
        return mediator.getPoints();
    }

    public void touchStart(float x, float y) {
        mediator.touchStart(x, y);
    }

    public void touchMove(float x, float y) {
        mediator.touchMove(x, y);
    }

    public void touchUp() {
        mediator.touchUp();
    }

    public void draw(Canvas canvas) {
        mediator.draw(canvas, bitmap);
    }
}
