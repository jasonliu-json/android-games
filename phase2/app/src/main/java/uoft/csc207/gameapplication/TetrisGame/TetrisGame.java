package uoft.csc207.gameapplication.TetrisGame;

import java.util.Timer;
import java.util.TimerTask;

class TetrisGame {

  /** The current falling piece. */
  private Piece fallingPiece;
  private Board board;
  private Timer timer;
  private Randomizer randomizer;
  private boolean gameIsOver;
  private int delay;
  private int points;
  private int linesCleared;
  private int threshold;

  TetrisGame(Board board, Randomizer randomizer) {
    this.board = board;
    this.randomizer = randomizer;
    fallingPiece = randomizer.nextPiece();
    gameIsOver = false;
    delay = 800; // piece falls every 800 ms at first
    points = 0;
    linesCleared = 0;
    threshold = 10;
    timer = new Timer();
    TimerTask makePieceFall =
            new TimerTask() {
              @Override
              public void run() {
                TetrisGame.this.moveDown();
              }
            };
    timer.scheduleAtFixedRate(makePieceFall, delay, delay);
  }

  Board getBoard() {
    return board;
  }

  /**
   * Returns whether this game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  boolean getGameIsOver() {
    return gameIsOver;
  }

  /**
   * Return the total points scored by the player.
   *
   * @return The total points scored by the player.
   */
  int getPoints() {
    return points;
  }

  private boolean tryMove(int adjX, int adjY) {
    board.removePieceFromBoard(fallingPiece);
    if (board.canMove(fallingPiece, adjX, adjY)) {
      fallingPiece.move(adjX, adjY);
      board.addPieceToBoard(fallingPiece);
      return true;
    }
    board.addPieceToBoard(fallingPiece);
    return false;
  }

  void moveLeft() {
    tryMove(-1, 0);
  }

  void moveRight() {
    tryMove(1, 0);
  }

  void moveDown() {
    if (!tryMove(0, 1)) {
      reset();
    }
  }

  void dropDown() {
    boolean isFalling = true;
    while (isFalling) {
      isFalling = tryMove(0, 1);
    }
    reset();
  }

  private void tryRotate(int direction) {
    board.removePieceFromBoard(fallingPiece);
    if (board.canRotate(fallingPiece, direction)) {
      fallingPiece.rotate(direction);
    }
    board.addPieceToBoard(fallingPiece);
  }

  void rotateClockwise() {
    tryRotate(1);
  }

  void rotateCounterClockwise() {
    tryRotate(-1);
  }

  private void reset() {
    linesCleared += board.clearLines();
    if (linesCleared == threshold) {
      accelerate();
      threshold += 10; // increase acceleration every 10 lines
    }
    fallingPiece = randomizer.nextPiece();
    if (board.canMove(fallingPiece, 0, 0)) {
      board.addPieceToBoard(fallingPiece);
    } else { // game over
      timer.cancel();
      gameIsOver = true;
    }
  }

  private void accelerate() {
    if (delay > 50) {
      timer.cancel();
      delay -= 50;
      timer = new Timer();
      TimerTask makePieceFall =
              new TimerTask() {
                @Override
                public void run() {
                  TetrisGame.this.moveDown();
                }
              };
      timer.scheduleAtFixedRate(makePieceFall, delay, delay);
    }
  }
}
