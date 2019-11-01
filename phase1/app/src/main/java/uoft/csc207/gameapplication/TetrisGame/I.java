package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the I piece. */
class I extends Piece {

  /** Constructs a new I piece object. */
  I() {
    super();
    states =
        new String[][] {
          {".....",
           ".....",
           "IIII.",
           ".....",
           "....."},
          {"..I..",
           "..I..",
           "..I..",
           "..I..",
           "....."}
        };
  }
}
