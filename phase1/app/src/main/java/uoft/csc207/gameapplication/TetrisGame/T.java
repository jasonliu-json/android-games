package uoft.csc207.gameapplication.TetrisGame;

class T extends Piece {

    T() {
        super();
        states = new String[][]{{".....",
                                 "..T..",
                                 ".TTT.",
                                 ".....",
                                 "....."},
                                {".....",
                                 "..T..",
                                 "..TT.",
                                 "..T..",
                                 "....."},
                                {".....",
                                 ".....",
                                 ".TTT.",
                                 "..T..",
                                 "....."},
                                {".....",
                                 "..T..",
                                 ".TT..",
                                 "..T..",
                                 "....."}};
    }
}
