package uoft.csc207.gameapplication.TetrisGame;

/** A class representing a piece in a game of Tetris. . */
class Piece {

  /** The x coordinate of this piece. */
  private int x;

  /** The y coordinate of this piece. */
  private int y;

  /** The index of the current rotated state of this piece. */
  private int rotation;

  /** String representations of this piece in each of its rotated states. */
  private String[][] states;

  /** Construct a new Piece object. */
  Piece(int x, int y, int rotation, String[][] states) {
    this.x = x;
    this.y = y;
    this.rotation = rotation;
    this.states = states;
  }

  int getRotation() {
    return rotation;
  }

  String[][] getStates() {
    return states;
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

  void move(int adjX, int adjY) {
    x += adjX;
    y += adjY;
  }

  void rotate(int direction) {
    rotation = (rotation + direction) % states.length;
  }
}
