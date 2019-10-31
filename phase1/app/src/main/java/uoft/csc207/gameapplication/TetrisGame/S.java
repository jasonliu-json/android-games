package uoft.csc207.gameapplication.TetrisGame;

class S extends Piece {

    S() {
        super();
        states = new String[][]{{".....",
                                 "..SS.",
                                 ".SS..",
                                 ".....",
                                 "....."},
                                {".....",
                                 ".S...",
                                 ".SS..",
                                 "..S..",
                                 "....."}};
    }
}
