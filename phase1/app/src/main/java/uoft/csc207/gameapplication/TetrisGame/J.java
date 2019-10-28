package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

public class J extends Piece {

    J() {
        super();
        color = Color.valueOf(60, 140, 255);
        shape = new String[][]{{".....",
                                ".X...",
                                ".XXX.",
                                ".....",
                                "....."},
                               {".....",
                                "..XX.",
                                "..X..",
                                "..X..",
                                "....."},
                               {".....",
                                ".....",
                                ".XXX.",
                                "...X.",
                                "....."},
                               {".....",
                                "..X..",
                                "..X..",
                                ".XX..",
                                "....."}};
    }
}
