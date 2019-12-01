package uoft.csc207.gameapplication.Games.TetrisGame;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

import uoft.csc207.gameapplication.Games.GameDriver;
import uoft.csc207.gameapplication.Games.TetrisGame.Controller.Request;
import uoft.csc207.gameapplication.Games.TetrisGame.Controller.TetrisGameController;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.Board;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.PieceGenerator;
import uoft.csc207.gameapplication.Games.TetrisGame.GameLogic.TetrisGame;
import uoft.csc207.gameapplication.Games.TetrisGame.Presenter.TetrisGamePresenter;

interface Command {
    void run();
}

/**
 * A facade class for the Tetris game, serving as an interface for managing touch inputs and
 * drawing the game to the screen.
 */
public class TetrisGameDriver extends GameDriver {

    /**
     * A representation of a game of Tetris, responsible for handling the logic of the game.
     */
    private TetrisGame game;

    /**
     * A class responsible translating user input to controls for the Tetris game.
     */
    private TetrisGameController controller;

    /**
     * A class responsible for presenting the Tetris game to the screen.
     */
    private TetrisGamePresenter presenter;

    /**
     * A map between a request to its respective Command object.
     */
    private Map<Request, Command> requestToCommand;

    /**
     * Initialize this TetrisGameDriver object.
     */
    public void init() {
        game = new TetrisGame(new Board(10, 20), new PieceGenerator());
        System.out.println(getColourScheme());
        presenter = new TetrisGamePresenter(getColourScheme());
        controller = new TetrisGameController(screenWidth, screenHeight);

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

    /**
     * Return true if and only if this game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean getGameIsOver() {
        return game.getGameIsOver();
    }

    /**
     * Return the points scored.
     *
     * @return The points scored.
     */
    public int getPoints() {
        return game.getPoints();
    }

    /**
     * Respond to the start of a touch event.
     *
     * @param x The x coordinate of the touch event.
     * @param y The y coordinate of the touch event.
     */
    public void touchStart(float x, float y) {
        controller.touchStart(x, y);
    }

    /**
     * Respond to the movement of a touch event.
     *
     * @param x The x coordinate of the touch event.
     * @param y The y coordinate of the touch event.
     */
    public void touchMove(float x, float y) {
        run(controller.touchMove(x, y));
    }

    /**
     * Respond to the end of a touch event.
     */
    public void touchUp() {
        run(controller.touchUp());
    }

    /**
     * Execute a Command object.
     *
     * @param request The player movement request to be executed.
     */
    private void run(Request request) {
        Command command = requestToCommand.get(request);
        if (command != null) {
            command.run();
        }
    }

    /**
     * Refresh the frame onscreen.
     *
     * @param canvas The canvas to be drawn on.
     */
    public void draw(Canvas canvas) {
        game.update();
        presenter.draw(canvas, bitmap, game.getGrid());
    }
}
