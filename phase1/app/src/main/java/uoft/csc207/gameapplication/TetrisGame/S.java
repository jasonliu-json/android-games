package uoft.csc207.gameapplication.TetrisGame;

class S extends Piece {

    S() {
        super();
        shape = new String[][]{{".....",
                                ".....",
                                "..SS.",
                                ".SS..",
                                "....."},
                               {".....",
                                "..S..",
                                "..SS.",
                                "...S.",
                                "....."}};
    }
}
