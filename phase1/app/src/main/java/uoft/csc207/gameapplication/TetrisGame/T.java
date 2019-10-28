package uoft.csc207.gameapplication.TetrisGame;

import android.graphics.Color;

public class T extends Piece {

    T() {
        super();
        color = Color.valueOf(190, 135, 255);
        String[][] shape = new String[][]{{".....",
                                           "..X..",
                                           ".XXX.",
                                           ".....",
                                           "....."},
                                          {".....",
                                           "..X..",
                                           "..XX.",
                                           "..X..",
                                           "....."},
                                          {".....",
                                           ".....",
                                           ".XXX.",
                                           "..X..",
                                           "....."},
                                          {".....",
                                           "..X..",
                                           ".XX..",
                                           "..X..",
                                           "....."}};
    }
}
