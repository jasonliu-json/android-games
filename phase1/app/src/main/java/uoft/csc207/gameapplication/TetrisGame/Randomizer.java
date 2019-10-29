package uoft.csc207.gameapplication.TetrisGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Piece generation class using the 7-Bag-Randomization algorithm.
 */
class Randomizer {

    private List<Character> bag;
    private int count;

    Randomizer() {
        count = 0;
        bag = new ArrayList<>(Arrays.asList('I', 'J', 'L', 'O', 'S', 'Z', 'T'));
        Collections.shuffle(bag);
    }

    Piece nextPiece() {
        if (count == 7) {   // reset
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
