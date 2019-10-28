package uoft.csc207.gameapplication.TetrisGame;

class PieceFactory {

    static Piece getRandomPiece(int type) {
        switch (type) {
            case 0:
                return new I();
            case 1:
                return new L();
            case 2:
                return new J();
            case 3:
                return new T();
            case 4:
                return new O();
            case 5:
                return new S();
            case 6:
                return new Z();
            default:
                return null;
        }
    }
}
