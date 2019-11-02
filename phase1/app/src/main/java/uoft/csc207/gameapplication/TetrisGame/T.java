package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the T piece. */
class T extends Piece {

  /** Constructs a new T piece object. */
  T() {
    super();
    states =
        new String[][] {
          {".....",
           "..T..",
           ".TTT.",
           ".....",
           "....."},
          {".....",
           "..T..",
           "..TT.",
           "..T..",
           "....."},
          {".....",
           ".....",
           ".TTT.",
           "..T..",
           "....."},
          {".....",
           "..T..",
           ".TT..",
           "..T..",
           "....."}
        };
  }
}
