package uoft.csc207.gameapplication.Games.TetrisGame;

import android.content.Context;
import android.graphics.Canvas;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.Controller.TetrisGameController;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.PieceGenerator;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.TetrisGame;
import uoft.csc207.gameapplication.Games.TetrisGame.Presenter.TetrisGamePresenter;

public class TetrisGameDriver extends GameDriver {

    private TetrisGame game;
    private TetrisGamePresenter presenter;
    private TetrisGameController controller;

    public TetrisGameDriver(Context context) {
        game = new TetrisGame(new Board(10, 20), new PieceGenerator());
        presenter = new TetrisGamePresenter(game);
        controller = new TetrisGameController(game);

        // CONFIG STRING

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
