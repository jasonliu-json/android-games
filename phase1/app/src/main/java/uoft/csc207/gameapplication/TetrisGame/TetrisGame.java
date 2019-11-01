package uoft.csc207.gameapplication.TetrisGame;

import java.util.Timer;
import java.util.TimerTask;

/** A TetrisGame class controlling game logic. */
class TetrisGame {

  /** The current piece being played. */
  private Piece fallingPiece;

  /** The board containing information about the location of pieces on the board. */
  private Board board;

  /** A random piece generator using the 7-Bag-Randomization algorithm. */
  private Randomizer pieceGenerator;

  /** Whether this game is running. */
  private boolean isRunning;

  /** Timer object used to make the current piece being played fall down 1 tile every 350 ms. */
  private Timer timer;

  /** Current score for this game. */
  private int score;

  /** Constructs a new TetrisGame object. */
  TetrisGame() {
    board = new Board();
    pieceGenerator = new Randomizer();
    fallingPiece = pieceGenerator.nextPiece();
    isRunning = true;

    // setup timer
    timer = new Timer();
    TimerTask makePieceFall =
        new TimerTask() {
          @Override
          public void run() {
            TetrisGame.this.moveFallingPieceDown();
          }
        };
    timer.scheduleAtFixedRate(makePieceFall, 350, 350); // piece falls every 350 ms
  }

  /**
   * Returns the board containing inforation about this game.
   *
   * @return The board for this instance of the game.
   */
  Board getBoard() {
    return board;
  }

  /**
   * Returns whether this game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  boolean getGameIsOver() {
    return !isRunning;
  }

  /**
   * Return the total points scored by the player.
   *
   * @return The total points scored by the player.
   */
  int getPoints() {
    return score;
  }

  /**
   * Moves the current piece being played down 1 tile if possible. Otherwise, generates a new piece
   * at the top of the screen.
   */
  void moveFallingPieceDown() {
    if (!fallingPiece.moveDown(board)) { // cannot move down
      score += board.clearRows();
      fallingPiece = pieceGenerator.nextPiece();
      if (fallingPiece.canMoveTo(board, 0, 0)) {
        fallingPiece.addPieceToBoard(board);
      } else {
        timer.cancel();
        isRunning = false;
      }
    }
  }

  /** Moves the current piece being payer 1 tile left if possible. */
  void moveFallingPieceLeft() {
    fallingPiece.moveLeft(board);
  }

  /** Moves the current piece being payer 1 tile right if possible. */
  void moveFallingPieceRight() {
    fallingPiece.moveRight(board);
  }

  /** Rotates the current piece clockwise if possible. */
  void rotateFallingPieceClockwise() {
    fallingPiece.rotateClockwise(board);
  }

  /** Rotates the current piece counterclockwise if possible. */
  void rotateFallingPieceCounterClockwise() {
    fallingPiece.rotateCounterClockwise(board);
  }

  /** Hard drops the current piece down if possible. */
  void dropFallingPieceDown() { // hard drop
    fallingPiece.dropDown(board);
  }
}
