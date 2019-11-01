package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the J piece. */
class J extends Piece {

  /** Constructs a new J piece object. */
  J() {
    super();
    states =
        new String[][] {
          {".....",
           ".J...",
           ".JJJ.",
           ".....",
           "....."},
          {".....",
           "..JJ.",
           "..J..",
           "..J..",
           "....."},
          {".....",
           ".....",
           ".JJJ.",
           "...J.",
           "....."},
          {".....",
           "..J..",
           "..J..",
           ".JJ..",
           "....."}
        };
  }
}
