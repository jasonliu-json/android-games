package uoft.csc207.gameapplication.TetrisGame;

abstract class Piece {

    private int x;
    private int y;
    private int rotation;
    String[][] states;

    Piece() {
        x = 3;   // center piece on x-axis of grid
        y = -1;   // fix piece on top of grid (actual coordinates are out of bounds)
        rotation = 0;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getRotation() {
        return rotation;
    }

    boolean canMoveTo(Board board, int adjX, int adjY) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[rotation][y].charAt(x) != '.') {
                    try {
                        if (board.getBoard()[this.y + y + adjY][this.x + x + adjX] != '.') {
                            return false;   // move results in collision
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;   // move is out of bounds
                    }
                }
            }
        }
        return true;
    }

    private boolean tryMove(Board board, int adjX, int adjY) {
        this.removePieceFromBoard(board);
        if (canMoveTo(board, adjX, adjY)) {
            this.x += adjX;
            this.y += adjY;
            addPieceToBoard(board);
            return true;
        }
        addPieceToBoard(board);
        return false;
    }

    void addPieceToBoard(Board board) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[rotation][y].charAt(x) != '.') {
                    board.getBoard()[this.y + y][this.x + x] = states[rotation][y].charAt(x);
                }
            }
        }
    }

    private void removePieceFromBoard(Board board) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[rotation][y].charAt(x) != '.') {
                    board.getBoard()[this.y + y][this.x + x] = '.';
                }
            }
        }
    }

    void moveLeft(Board board) {
        tryMove(board, -1, 0);
    }

    void moveRight(Board board) {
        tryMove(board, 1, 0);
    }

    boolean moveDown(Board board) {
        return tryMove(board, 0, 1);
    }

    void dropDown(Board board) {
        while (tryMove(board, 0, 1)) {
            moveDown(board);
        }
    }

    private boolean canRotate(Board board, int direction) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (states[(rotation + direction) % states.length][y].charAt(x) != '.') {
                    try {
                        if (board.getBoard()[this.y + y][this.x + x] != '.') {
                            return false;   // rotation results in collision
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;   // rotation is out of bounds
                    }
                }
            }
        }
        return true;
    }

    private void tryRotate(Board board, int direction) {
        removePieceFromBoard(board);
        if (canRotate(board, direction)) {
            System.out.println("START" + rotation);
            rotation = (rotation + direction) % states.length;
            System.out.println("END" + rotation);
        }
        addPieceToBoard(board);
    }

    void rotateClockwise(Board board) {
        tryRotate(board, 1);
    }

    void rotateCounterClockwise(Board board) {
        tryRotate(board, -1);
    }
}
