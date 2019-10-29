package uoft.csc207.gameapplication.TetrisGame;

class J extends Piece {

    J() {
        super();
        states = new String[][]{{".....",
                                 ".J...",
                                 ".JJJ.",
                                 ".....",
                                 "....."},
                                {".....",
                                 "..JJ.",
                                 "..J..",
                                 "..J..",
                                 "....."},
                                {".....",
                                 ".....",
                                 ".JJJ.",
                                 "...J.",
                                 "....."},
                                {".....",
                                 "..J..",
                                 "..J..",
                                 ".JJ..",
                                 "....."}};
    }
}
