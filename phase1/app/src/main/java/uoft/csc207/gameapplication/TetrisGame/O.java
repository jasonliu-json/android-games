package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

public class O extends Piece {

    O() {
        super();
        color = Color.valueOf(255, 225, 85);
        shape = new String[][]{{".....",
                                ".....",
                                ".XX..",
                                ".XX..",
                                "....."}};
    }
}
