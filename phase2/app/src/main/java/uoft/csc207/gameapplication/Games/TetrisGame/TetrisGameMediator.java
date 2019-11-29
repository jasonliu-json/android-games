package uoft.csc207.gameapplication.Games.TetrisGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import uoft.csc207.gameapplication.Games.TetrisGame.Controller.TetrisGameController;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.TetrisGame;
import uoft.csc207.gameapplication.Games.TetrisGame.Presenter.TetrisGamePresenter;

/**
 * A mediator class FINISH
 */
public class TetrisGameMediator {

    private TetrisGame game;
    private TetrisGamePresenter presenter;
    private TetrisGameController controller;

    void setGame(TetrisGame game) {
        this.game = game;
    }

    void setPresenter(TetrisGamePresenter presenter) {
        this.presenter = presenter;
    }

    void setController(TetrisGameController controller) {
        this.controller = controller;
    }

    boolean getGameIsOver() {
        return game.getGameIsOver();
    }

    int getPoints() {
        return game.getPoints();
    }

    void touchStart(float x, float y) {
        controller.touchStart(x, y);
    }

    void touchMove(float x, float y) {
        controller.touchMove(x, y);
    }

    void touchUp() {
        controller.touchUp();
    }

    public void draw(Canvas canvas, Bitmap bitmap) {
        game.update();
        presenter.draw(canvas, bitmap);
    }

    public void moveLeft() {
        game.moveLeft();
    }

    public void moveRight() {
        game.moveRight();
    }

    public void moveDown() {
        game.moveDown();
    }

    public void dropDown() {
        game.dropDown();
    }

    public void rotateClockwise() {
        game.rotateClockwise();
    }

    public void rotateCounterClockwise() {
        game.rotateCounterClockwise();
    }

    public Board getBoard() {
        return game.getBoard();
    }
}
