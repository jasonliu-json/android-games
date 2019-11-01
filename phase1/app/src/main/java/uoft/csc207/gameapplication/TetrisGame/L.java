package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the L piece. */
class L extends Piece {

  /** Constructs a new L piece object. */
  L() {
    super();
    states =
        new String[][] {
          {".....",
           "...L.",
           ".LLL.",
           ".....",
           "....."},
          {".....",
           "..L..",
           "..L..",
           "..LL.",
           "....."},
          {".....",
           ".....",
           ".LLL.",
           ".L...",
           "....."},
          {".....",
           ".LL..",
           "..L..",
           "..L..",
           "....."}
        };
  }
}
