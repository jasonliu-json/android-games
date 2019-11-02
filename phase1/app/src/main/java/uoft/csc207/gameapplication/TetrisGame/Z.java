package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the Z piece. */
class Z extends Piece {

  /** Constructs a new Z piece objects. */
  Z() {
    super();
    states =
        new String[][] {
          {".....",
           ".ZZ..",
           "..ZZ.",
           ".....",
           "....."},
          {"..Z..",
           ".ZZ..",
           ".Z...",
           ".....",
           "....."}
        };
  }
}
