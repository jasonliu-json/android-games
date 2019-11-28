package uoft.csc207.gameapplication.TetrisGame.GameLogic;

public class TetrisGame {

    private Board board;
    private PieceGenerator pieceGenerator;
    private boolean gameIsOver;
    private int points;
    private int totalLines;
    private int rate;
    private int count;

    /**
     * Construct a new TetrisGame object.
     */
    public TetrisGame(Board board, PieceGenerator pieceGenerator) {
        this.board = board;
        this.pieceGenerator = pieceGenerator;
        board.setCurrPiece(pieceGenerator.nextPiece());
        gameIsOver = false;
        points = 0;
        totalLines = 0;
        rate = 25;  // 25 frames
        count = 0;
    }

    /**
     * Return .
     *
     * @return True if the game is over, false otherwise.
     */
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
     * Return the points scored.
     *
     * @return The points scored.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Move the current piece left, if possible.
     */
    public void moveLeft() {
        board.moveLeft();
    }

    /**
     * Move the current piece right, if possible.
     */
    public void moveRight() {
        board.moveRight();
    }

    /**
     * Move the current piece down, if possible.
     */
    public void moveDown() {
        board.moveDown();
        if (board.getCurrPiece() == null) {
            reset();
        }
    }

    /**
     * Drop the current piece all the way down, if possible.
     */
    public void dropDown() {
        board.dropDown();
        reset();
    }

    /**
     * Periodically move the current piece down.
     */
    public void fallDown() {
        if (count == rate) {
            moveDown();
            count = 0;
            if (totalLines >= 10) {
                rate = Math.max(5, rate - 5);
                totalLines = totalLines % 10;
                System.out.println(rate);
            }
        } else {
            count += 1;
        }
    }

    /**
     * Rotate the current piece clockwise, if possible.
     */
    public void rotateClockwise() {
        board.rotateClockwise();
    }

    /**
     * Rotate the current piece counterclockwise, if possible.
     */
    public void rotateCounterClockwise() {
        board.rotateCounterClockwise();
    }

    /**
     * Rotate the current piece counterclockwise, if possible.
     */
    private void reset() {
        int lines = board.clearLines();
        totalLines += lines;
        points += lines * 100;
        if (!board.setCurrPiece(pieceGenerator.nextPiece())) {
            gameIsOver = true;
        }
    }
}
