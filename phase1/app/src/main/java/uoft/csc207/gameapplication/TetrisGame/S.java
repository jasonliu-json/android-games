package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

public class S extends Piece {

    S() {
        super();
        color = Color.valueOf(145, 255, 130);
        shape = new String[][]{{".....",
                                ".....",
                                "..XX.",
                                ".XX..",
                                "....."},
                               {".....",
                                "..X..",
                                "..XX.",
                                "...X.",
                                "....."}};
    }
}
