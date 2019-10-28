package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

public class L extends Piece {

    L() {
        super();
        color = Color.valueOf(255, 200, 100);
        String[][] shape = new String[][]{{".....",
                                           "...X.",
                                           ".XXX.",
                                           ".....",
                                           "....."},
                                          {".....",
                                           "..X..",
                                           "..X..",
                                           "..XX.",
                                           "....."},
                                          {".....",
                                           ".....",
                                           ".XXX.",
                                           ".X...",
                                           "....."},
                                          {".....",
                                          ".XX..",
                                          "..X..",
                                          "..X..",
                                          "....."}};
    }
}
