package uoft.csc207.gameapplication.TetrisGame;

/** A class representing the O piece. */
class O extends Piece {

  /** Constructs a new O piece object. */
  O() {
    super();
    states = new String[][] {{".....",
                              ".OO..",
                              ".OO..",
                              ".....",
                              "....."}};
  }
}
