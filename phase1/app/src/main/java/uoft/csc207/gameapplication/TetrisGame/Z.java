package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

public class Z extends Piece {

    Z() {
        super();
        color = Color.valueOf(255, 100, 100);
        shape = new String[][]{{".....",
                                ".....",
                                ".XX..",
                                "..XX.",
                                "....."},
                               {".....",
                                "..X..",
                                ".XX..",
                                ".X...",
                                "....."}};
    }
}
