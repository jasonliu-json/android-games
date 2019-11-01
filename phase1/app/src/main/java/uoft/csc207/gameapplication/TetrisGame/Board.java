package uoft.csc207.gameapplication.TetrisGame;

import java.util.Arrays;

/** A board class holding information about the positions of each Tetris block onscreen. */
class Board {

  /** The width of the board. */
  private int width = 10;

  /** The height of the board. */
  private int height = 20;

  /**
   * A 2D array containing character representations ('I', 'J', 'L', 'O', 'S', 'Z', 'T') of the
   * current pieces on the screen in their respective positions. Empty positions are represented by
   * '.'.
   */
  private char[][] board;

  /** Constructs a new Board object. */
  Board() {
    board = new char[height][width];
    for (int i = 0; i < height; i++) {
      Arrays.fill(board[i], '.');
    }
  }

  /**
   * Returns this board.
   *
   * @return The 2D array representing the board.
   */
  char[][] getBoard() {
    return board;
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

  /**
   * Returns true if row n contains no empty positions.
   *
   * @return True if row n is full, false otherwise.
   */
  private boolean rowIsFull(int n) {
    for (int x = 0; x < width; x++) {
      if (board[n][x] == '.') {
        return false;
      }
    }
    return true;
  }

  /** Clears all pieces in row n and shifts all entries above down by one row. */
  private void clearRow(int n) {
    for (int y = n; y > 0; y--) { // updates rows 1-n
      for (int x = 0; x < width; x++) {
        board[y][x] = board[y - 1][x];
      }
    }
    Arrays.fill(board[0], '.'); // updates row 0
  }

  /**
   * Examines the board and clears any full rows.
   *
   * @return The score accumulated by the player for clearing each row.
   */
  int clearRows() {
    int score = 0;
    for (int y = 0; y < height; y++) {
      if (this.rowIsFull(y)) {
        this.clearRow(y);
        score += 100;
      }
    }
    return score;
  }
}
