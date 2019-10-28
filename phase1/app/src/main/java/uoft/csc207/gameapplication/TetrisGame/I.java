package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

class I extends Piece {

    I() {
        super();
        color = Color.valueOf(110, 245, 255);
        shape = new String[][]{{".....",
                                ".....",
                                "XXXX.",
                                ".....",
                                "....."},
                               {"..X..",
                                "..X..",
                                "..X..",
                                "..X..",
                                "....."}};
    }

}
