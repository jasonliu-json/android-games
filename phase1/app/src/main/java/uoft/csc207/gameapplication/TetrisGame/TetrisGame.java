package uoft.csc207.gameapplication.TetrisGame;

import java.util.Timer;
import java.util.TimerTask;

class TetrisGame {

  /** The current falling piece. */
  private Piece fallingPiece;

  Board getBoard() {
    return board;
  }

  private Board board;
  private Timer timer;
  private Randomizer randomizer;
  private boolean gameIsOver;
  private int delay;
  private int points;

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

  TetrisGame(Board board, Randomizer randomizer) {
    this.board = board;
    this.randomizer = randomizer;
    gameIsOver = false;
    delay = 800; // piece falls every 800 ms at first
    points = 0;
    nextFallingPiece();
    beginTimer();
  }

  private boolean move(int adjX, int adjY) {
    boolean hasMoved = false;
    board.removePieceFromBoard(fallingPiece);
    if (board.canMove(fallingPiece, adjX, adjY)) {
      fallingPiece.move(adjX, adjY);
      hasMoved = true;
    }
    board.addPieceToBoard(fallingPiece);
    return hasMoved;
  }

  void moveLeft() {
    move(-1, 0);
  }

  void moveRight() {
    move(1, 0);
  }

  void moveDown() {
    boolean hasMoved = move(0, 1);
    if (!hasMoved) {
      points += board.clearRows();
      if (points % 1500 == 0) { // increase acceleration every 1500 points
        accelerateTimer();
      }
      nextFallingPiece();
    }
  }

  void dropDown() {
    while (board.canMove(fallingPiece, 0, 1)) {
      moveDown();
    }
  }

  private void rotate(int direction) {
    board.removePieceFromBoard(fallingPiece);
    if (board.canRotate(fallingPiece, direction)) {
      fallingPiece.rotate(direction);
    }
    board.addPieceToBoard(fallingPiece);
  }

  void rotateClockwise() {
    rotate(1);
  }

  void rotateCounterClockwise() {
    rotate(-1);
  }

  private void nextFallingPiece() {
    fallingPiece = randomizer.nextPiece();
    if (board.canMove(fallingPiece, 0, 0)) {
      board.addPieceToBoard(fallingPiece);
    } else {
      timer.cancel();
      gameIsOver = true;
    }
  }

  private void beginTimer() {
    timer = new Timer();
    TimerTask makePieceFall =
        new TimerTask() {
          @Override
          public void run() {
            TetrisGame.this.moveDown();
          }
        };
    timer.scheduleAtFixedRate(makePieceFall, 2 * delay, delay);
  }

  private void endTimer() {
    timer.cancel();
  }

  private void accelerateTimer() {
    endTimer();
    if (delay > 50) {
      delay -= 50;
    }
    beginTimer();
  }
}
