package uoft.csc207.gameapplication.Games.TetrisGame;

import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.HashMap;
import java.util.Map;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.Controller.TetrisGameController;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.PieceGenerator;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.TetrisGame;
import uoft.csc207.gameapplication.Games.TetrisGame.Presenter.TetrisGamePresenter;

public class TetrisGameDriver extends GameDriver {

    private TetrisGame game;
    private TetrisGameController controller;
    private TetrisGamePresenter presenter;

    private Map<Request, Command> requestToCommand;

    private String config;
//    private DisplayMetrics metrics;

//    public void setConfigurations(String config) {
//        this.config = config;
//    }

    public void init() {
        super.init();
        game = new TetrisGame(new Board(10, 20), new PieceGenerator());
        presenter = new TetrisGamePresenter("default");
        controller = new TetrisGameController(getMetrics());

        requestToCommand = new HashMap<>();
        requestToCommand.put(Request.MOVE_LEFT, new Command() {
            @Override
            public void run() {
                game.moveLeft();
            }
        });
        requestToCommand.put(Request.MOVE_RIGHT, new Command() {
            @Override
            public void run() {
                game.moveRight();
            }
        });
        requestToCommand.put(Request.MOVE_DOWN, new Command() {
            @Override
            public void run() {
                game.moveDown();
            }
        });
        requestToCommand.put(Request.DROP_DOWN, new Command() {
            @Override
            public void run() {
                game.dropDown();
            }
        });
        requestToCommand.put(Request.ROTATE_CLOCKWISE, new Command() {
            @Override
            public void run() {
                game.rotateClockwise();
            }
        });
        requestToCommand.put(Request.ROTATE_COUNTERCLOCKWISE, new Command() {
            @Override
            public void run() {
                game.rotateCounterClockwise();
            }
        });
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
        run(controller.touchMove(x, y));
    }

    public void touchUp() {
        run(controller.touchUp());
    }

    private void run(Request request) {
        Command command = requestToCommand.get(request);
        if (command != null) {
            command.run();
        }
    }

    public void draw(Canvas canvas) {
        game.update();
        presenter.draw(canvas, bitmap, game.getGrid());
    }
}
