package uoft.csc207.gameapplication.TetrisGame;

import android.content.Context;
import android.graphics.Canvas;

import uoft.csc207.gameapplication.GameDriver;
import uoft.csc207.gameapplication.TetrisGame.Controller.TetrisGameController;
import uoft.csc207.gameapplication.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.TetrisGame.GameLogic.Randomizer;
import uoft.csc207.gameapplication.TetrisGame.GameLogic.TetrisGame;
import uoft.csc207.gameapplication.TetrisGame.Presenter.TetrisGamePresenter;

public class TetrisGameDriver extends GameDriver {

    private TetrisGame game;
    private TetrisGamePresenter presenter;
    private TetrisGameController controller;

    public TetrisGameDriver(Context context) {
        game = new TetrisGame(new Board(10, 20), new Randomizer());
        presenter = new TetrisGamePresenter(game);
        controller = new TetrisGameController(game);
    }


    public boolean getGameIsOver() {
        return game.getGameIsOver();
    }

    public int getPoints() {
        return game.getPoints();
    }

    public void touchStart(float x, float y) {
        controller.touchStart(x, y);
    }

    public void touchMove(float x, float y) {
        controller.touchMove(x, y);
    }

    public void touchUp() {
        controller.touchUp();
    }

    public void draw(Canvas canvas) {
        game.fallDown();
        presenter.draw(canvas, bitmap);
    }
}
