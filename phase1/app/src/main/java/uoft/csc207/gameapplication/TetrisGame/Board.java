package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;

/** A class representing the playing board in a game of Tetris. . */
class Board {

  /** The width of this board. */
  private int width;

  /** The height of this board. */
  private int height;

  /** A 2D array representation of this board. */
  private char[][] grid;

  /** Construct a new Board object. */
  Board(int width, int height) {
    this.width = width;
    this.height = height;
    grid = new char[height][width];
    for (int i = 0; i < height; i++) {
      Arrays.fill(grid[i], '.');
    }
  }

  /**
   * Returns this grid.
   *
   * @return The 2D array representing the board.
   */
  char[][] getGrid() {
    return grid;
  }

  /**
   * Returns the width of this board.
   *
   * @return The width of this board.
   */
  int getWidth() {
    return width;
  }

  /**
   * Returns the height of this board.
   *
   * @return The height of this board.
   */
  int getHeight() {
    return height;
  }

  /** Clears all pieces in row n and shifts all entries above down by one row. */
  private void clearRow(int n) {
    for (int y = n; y > 0; y--) { // updates rows 1-n
      grid[y] = grid[y - 1].clone();
    }
    Arrays.fill(grid[0], '.'); // updates row 0
  }

  /**
   * Clear all filled rows on this board.
   *
   * @return The score accumulated by the player for the rows cleared.
   */
  int clearRows() {
    int points = 0;
    for (int y = 0; y < height; y++) {
      boolean isFull = true;
      for (int x = 0; x < width; x++) {
        if (grid[y][x] == '.') {
          isFull = false;
          break;
        }
      }
      if (isFull) {
        clearRow(y);
        points += 100;
      }
    }
    return points;
  }

  /**
   * Draw a piece onto this board.
   *
   * @param piece The piece to be added.
   */
  void addPieceToBoard(Piece piece) {
    int length = piece.getStates()[0][0].length();
    for (int y = 0; y < length; y++) {
      for (int x = 0; x < length; x++) {
        if (piece.getStates()[piece.getRotation()][y].charAt(x) != '.') {
          grid[piece.getY() + y][piece.getX() + x] =
              piece.getStates()[piece.getRotation()][y].charAt(x);
        }
      }
    }
  }

  /**
   * Erase a piece from this board.
   *
   * @param piece The piece to be removed.
   */
  void removePieceFromBoard(Piece piece) {
    int length = piece.getStates()[0][0].length();
    for (int y = 0; y < length; y++) {
      for (int x = 0; x < length; x++) {
        if (piece.getStates()[piece.getRotation()][y].charAt(x) != '.') {
          grid[piece.getY() + y][piece.getX() + x] = '.';
        }
      }
    }
  }

  /** */
  boolean canMove(Piece piece, int adjX, int adjY) {
    int length = piece.getStates()[0][0].length();
    for (int y = 0; y < length; y++) {
      for (int x = 0; x < length; x++) {
        if (piece.getStates()[piece.getRotation()][y].charAt(x) != '.') {
          try {
            int newX = piece.getX() + x + adjX;
            int newY = piece.getY() + y + adjY;
            if (grid[newY][newX] != '.') {
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

  /** */
  boolean canRotate(Piece piece, int direction) {
    int length = piece.getStates()[0][0].length();
    for (int y = 0; y < length; y++) {
      for (int x = 0; x < length; x++) {
        int newRotation = (piece.getRotation() + direction) % piece.getStates().length;
        if (piece.getStates()[newRotation][y].charAt(x) != '.') {
          try {
            if (grid[piece.getY() + y][piece.getX() + x] != '.') {
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
}
