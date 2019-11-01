package uoft.csc207.gameapplication.TetrisGame;

/**
 * An abstract piece class containing information about this piece's position on the game board,
 * rotation, and shape.
 */
abstract class Piece {

  /** The x coordinate of this piece. */
  private int x;

  /** The y coordinate of this piece. */
  private int y;

  /** The index of the current rotated state of this piece. */
  private int rotation;

  /** An array holding String representations of this piece in each of its rotated states. */
  String[][] states;

  /** Constructs a new Piece object. */
  Piece() {
    x = 3; // center piece on x-axis of grid
    y = -1; // fix piece on top of grid (actual coordinates are out of bounds)
    rotation = 0;
  }

  /**
   * Return the x coordinate of this piece.
   *
   * @return The x coordinate of this piece.
   */
  int getX() {
    return x;
  }

  /**
   * Return the y coordinate of this piece.
   *
   * @return The y coordinate of this piece.
   */
  int getY() {
    return y;
  }

  /**
   * Returns true if this piece can move adjX tiles in the x direction and adjY tiles in the y
   * direction.
   *
   * @param board The board this piece attempts to move on.
   * @param adjX The number of tiles this piece attempts to move in the x direction.
   * @param adjY The number of tiles this piece attempts to move in the y direction.
   * @return True if this piece is able to move, false otherwise.
   */
  boolean canMoveTo(Board board, int adjX, int adjY) {
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        if (states[rotation][y].charAt(x) != '.') {
          try {
            if (board.getBoard()[this.y + y + adjY][this.x + x + adjX] != '.') {
              return false; // move results in collision
            }
          } catch (IndexOutOfBoundsException e) {
            return false; // move is out of bounds
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if this piece successfully moves in adjX tiles in the x direction and adjY tiles
   * in the y direction.
   *
   * @param board The board this piece moves on.
   * @param adjX The number of tiles this piece moves in the x direction.
   * @param adjY The number of tiles this piece moves in the y direction.
   * @return True if this piece successfully moves in the desired direction, false otherwise.
   */
  private boolean tryMove(Board board, int adjX, int adjY) {
    this.removePieceFromBoard(board);
    if (canMoveTo(board, adjX, adjY)) {
      this.x += adjX;
      this.y += adjY;
      addPieceToBoard(board);
      return true;
    }
    addPieceToBoard(board);
    return false;
  }

  /**
   * Draws this piece onto the board in its correct position.
   *
   * @param board The board to be drawn on.
   */
  void addPieceToBoard(Board board) {
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        if (states[rotation][y].charAt(x) != '.') {
          board.getBoard()[this.y + y][this.x + x] = states[rotation][y].charAt(x);
        }
      }
    }
  }

  /**
   * Erases this piece from the board.
   *
   * @param board The board to be erased from.
   */
  private void removePieceFromBoard(Board board) {
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        if (states[rotation][y].charAt(x) != '.') {
          board.getBoard()[this.y + y][this.x + x] = '.';
        }
      }
    }
  }

  /**
   * Move this piece 1 tile left.
   *
   * @param board The board to be moved on.
   */
  void moveLeft(Board board) {
    tryMove(board, -1, 0);
  }

  /**
   * If possible, move this piece 1 tile right.
   *
   * @param board The board to be moved on.
   */
  void moveRight(Board board) {
    tryMove(board, 1, 0);
  }

  /**
   * If possible, move this piece 1 tile down.
   *
   * @param board The board to be moved on.
   */
  boolean moveDown(Board board) {
    return tryMove(board, 0, 1);
  }

  /**
   * If possible, move this piece down until it cannot move any farther.
   *
   * @param board The board to be moved on.
   */
  void dropDown(Board board) {
    while (tryMove(board, 0, 1)) {
      moveDown(board);
    }
  }

  /**
   * Returns true if this piece can be rotated 90 degrees.
   *
   * @param board The board to be rotated on.
   * @param direction The direction to be rotated (1 is clockwise, -1 is counterclockwise).
   * @return True if this piece is able to rotate, false otherwise.
   */
  private boolean canRotate(Board board, int direction) {
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        if (states[(rotation + direction) % states.length][y].charAt(x) != '.') {
          try {
            if (board.getBoard()[this.y + y][this.x + x] != '.') {
              return false; // rotation results in collision
            }
          } catch (IndexOutOfBoundsException e) {
            return false; // rotation is out of bounds
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns true if this piece successfully rotates.
   *
   * @param board The board to be rotated on.
   * @param direction The direction to be rotated (1 for clockwise, -1 for counterclockwise).
   */
  private void tryRotate(Board board, int direction) {
    removePieceFromBoard(board);
    if (canRotate(board, direction)) {
      System.out.println("START" + rotation);
      rotation = (rotation + direction) % states.length;
      System.out.println("END" + rotation);
    }
    addPieceToBoard(board);
  }

  /**
   * If possible, rotate this piece clockwise.
   *
   * @param board The board to be rotated on.
   */
  void rotateClockwise(Board board) {
    tryRotate(board, 1);
  }

  /**
   * If possible, rotate this piece counterclockwise.
   *
   * @param board The board to be rotated on.
   */
  void rotateCounterClockwise(Board board) {
    tryRotate(board, -1);
  }
}
