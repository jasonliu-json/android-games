package uoft.csc207.gameapplication.TetrisGame.GameLogic;

public class TetrisGame {

    /**
     * The current falling piece.
     */
    private Piece fallingPiece;
    private Board board;
    private Randomizer randomizer;
    private boolean gameIsOver;
    private int points;
    private int linesCleared;
    private int threshold;
    private int rate;
    private int count;

    public TetrisGame(Board board, Randomizer randomizer) {
        this.board = board;
        this.randomizer = randomizer;
        fallingPiece = randomizer.nextPiece();
        gameIsOver = false;
        points = 0;
        linesCleared = 0;
        threshold = 1;
        rate = 30;  // piece falls every 30 * <refresh rate> ms
        count = 0;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Returns whether this game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean getGameIsOver() {
        return gameIsOver;
    }

    /**
     * Return the total points scored by the player.
     *
     * @return The total points scored by the player.
     */
    public int getPoints() {
        return points;
    }

    private boolean tryMove(int adjX, int adjY) {
        board.removePiece(fallingPiece);
        if (board.canMove(fallingPiece, adjX, adjY)) {
            fallingPiece.move(adjX, adjY);
            board.addPiece(fallingPiece);
            return true;
        }
        board.addPiece(fallingPiece);
        return false;
    }

    public void moveLeft() {
        tryMove(-1, 0);
    }

    public void moveRight() {
        tryMove(1, 0);
    }

    public void moveDown() {
        if (!tryMove(0, 1)) {
            reset();
        }
    }

    public void fallDown() {
        if (count == rate) {
            moveDown();
            count = 0;
        } else {
            count += 1;
        }
    }

    public void dropDown() {
        boolean isFalling = true;
        while (isFalling) {
            isFalling = tryMove(0, 1);
        }
        reset();
    }

    private void tryRotate(int direction) {
        board.removePiece(fallingPiece);
        if (board.canRotate(fallingPiece, direction)) {
            fallingPiece.rotate(direction);
        }
        board.addPiece(fallingPiece);
    }

    public void rotateClockwise() {
        tryRotate(1);
    }

    public void rotateCounterClockwise() {
        tryRotate(fallingPiece.getSprites().length - 1);
    }

    private void reset() {
        linesCleared += board.clearLines();
        points = linesCleared * 100;
        if (linesCleared >= threshold && rate > 5) {
            rate -= 5;
            count = 0;
            threshold += 10;
        }
        resetFallingPiece();
    }

    private void resetFallingPiece() {
        fallingPiece = randomizer.nextPiece();
        if (board.canMove(fallingPiece, 0, 0)) {
            board.addPiece(fallingPiece);
        } else { // game over
            gameIsOver = true;
        }
    }
}
