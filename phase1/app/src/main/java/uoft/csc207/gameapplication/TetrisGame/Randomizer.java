package uoft.csc207.gameapplication.TetrisGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Piece generation class using the 7-Bag-Randomization algorithm.
 *
 * The 7-Bag-Randomization algorithm generates a sequence of all 7 pieces permuted randomly, as
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
    switch (bag.get(count++)) {
      case 'I':
        return new I();
      case 'J':
        return new J();
      case 'L':
        return new L();
      case 'O':
        return new O();
      case 'S':
        return new S();
      case 'Z':
        return new Z();
      case 'T':
        return new T();
      default:
        return null;
    }
  }
}
