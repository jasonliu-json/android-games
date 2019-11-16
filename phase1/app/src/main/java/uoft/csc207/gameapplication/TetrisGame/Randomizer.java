package uoft.csc207.gameapplication.TetrisGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Piece generation class using the 7-Bag-Randomization algorithm.
 *
 * <p>The 7-Bag-Randomization algorithm generates a sequence of all 7 pieces permuted randomly, as
 * if they were drawn from a bag. All 7 pieces are then dealt before generating another bag. This
 * ensures that the player does not get an extremely long drought without a desired piece.
 */
class Randomizer {

  /** Permutation of 7 pieces in the bag, represented by characters. */
  private List<Character> bag;

  /** Number of pieces that have already been dealt from the current bag. */
  private int count;

  /** Constructs a new Randomizer object. */
  Randomizer() {
    count = 0;
    bag = new ArrayList<>(Arrays.asList('I', 'J', 'L', 'O', 'S', 'Z', 'T'));
    Collections.shuffle(bag);
  }

  /**
   * Returns a pseudo-random piece and generates a new bag if all 7 pieces have been dealt.
   *
   * @return The piece to be dealt.
   */
  Piece nextPiece() {
    if (count == 7) { // all 7 pieces have been dealt
      Collections.shuffle(bag);
      count = 0;
    }
    String[][] states;
    switch (bag.get(count++)) {
      case 'I':
        states =
            new String[][] {
              {".....", ".....", "IIII.", ".....", "....."},
              {"..I..", "..I..", "..I..", "..I..", "....."}
            };
        break;
      case 'J':
        states =
            new String[][] {
              {".....", ".J...", ".JJJ.", ".....", "....."},
              {".....", "..JJ.", "..J..", "..J..", "....."},
              {".....", ".....", ".JJJ.", "...J.", "....."},
              {".....", "..J..", "..J..", ".JJ..", "....."}
            };
        break;
      case 'L':
        states =
            new String[][] {
              {".....", "...L.", ".LLL.", ".....", "....."},
              {".....", "..L..", "..L..", "..LL.", "....."},
              {".....", ".....", ".LLL.", ".L...", "....."},
              {".....", ".LL..", "..L..", "..L..", "....."}
            };
        break;
      case 'O':
        states = new String[][] {{".....", ".OO..", ".OO..", ".....", "....."}};
        break;
      case 'S':
        states =
            new String[][] {
              {".....", "..SS.", ".SS..", ".....", "....."},
              {".....", ".S...", ".SS..", "..S..", "....."}
            };
        break;
      case 'Z':
        states =
            new String[][] {
              {".....", ".ZZ..", "..ZZ.", ".....", "....."},
              {"..Z..", ".ZZ..", ".Z...", ".....", "....."}
            };
        break;
      case 'T':
        states =
            new String[][] {
              {".....", "..T..", ".TTT.", ".....", "....."},
              {".....", "..T..", "..TT.", "..T..", "....."},
              {".....", ".....", ".TTT.", "..T..", "....."},
              {".....", "..T..", ".TT..", "..T..", "....."}
            };
        break;
      default:
        states = new String[][] {};
    }
    return new Piece(3, -1, 0, states);
  }
}
