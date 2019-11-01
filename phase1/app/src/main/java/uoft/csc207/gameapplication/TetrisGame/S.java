package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the S piece. */
class S extends Piece {

  /** Constructs a new S piece object. */
  S() {
    super();
    states =
        new String[][] {
          {".....",
           "..SS.",
           ".SS..",
           ".....",
           "....."},
          {".....",
           ".S...",
           ".SS..",
           "..S..",
           "....."}
        };
  }
}
