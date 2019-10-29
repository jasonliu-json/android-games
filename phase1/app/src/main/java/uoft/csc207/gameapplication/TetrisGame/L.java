package uoft.csc207.gameapplication.TetrisGame;

class L extends Piece {

    L() {
        super();
        states = new String[][]{{".....",
                                 "...L.",
                                 ".LLL.",
                                 ".....",
                                 "....."},
                                {".....",
                                 "..L..",
                                 "..L..",
                                 "..LL.",
                                 "....."},
                                {".....",
                                 ".....",
                                 ".LLL.",
                                 ".L...",
                                 "....."},
                                {".....",
                                 ".LL..",
                                 "..L..",
                                 "..L..",
                                 "....."}};
    }
}
