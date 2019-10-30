package uoft.csc207.gameapplication.TetrisGame;

import java.util.Timer;
import java.util.TimerTask;

/**
 * IMPORTANT MESSAGE:
 *
 * Basically, all you have to do is create an instance of TetrisGame in TetrisGameDriver and hook
 * up moveFallingPieceDown, moveFallingPieceLeft, moveFallingPieceRight to their respective
 * user inputs in TetrisGameDriver.touchMove.
 *
 * This class, whether directly or indirectly, calculates everything regarding piece collision,
 * clearing lines whenever necessary, moving fallingPiece down at constant time intervals, etc.
 *
 * The only information you need to draw the game onto the screen is contained in the attribute
 * board, which you can call using getBoard (I think this should be done in the
 * TetrisGameDriver.draw method, which is called every 30 ms).
 *
 * All you have to do is parse this 2D array and draw the shapes based on the entries, i.e. '.' is
 * empty, 'I' should be a cyan tile, 'O' should be a yellow tile, etc.
 *
 */
class TetrisGame {

    private Piece fallingPiece;

    private BoardV2 board;
    private Randomizer pieceGenerator;

    private Timer timer;

    private int score;   // not used at the moment

    TetrisGame() {
        board = new BoardV2();
        pieceGenerator = new Randomizer();
        fallingPiece = pieceGenerator.nextPiece();

        // setup timer
        timer = new Timer();
        TimerTask makePieceFall = new TimerTask() {
            @Override
            public void run() {
                TetrisGame.this.moveFallingPieceDown();
            }
        };
        timer.scheduleAtFixedRate(makePieceFall, 500, 500);   // piece falls every 500 ms
    }

    BoardV2 getBoard() {
        return board;
    }

    void moveFallingPieceDown() {
        board.removePiece(fallingPiece);
        if (!fallingPiece.moveDown(board)) {   // cannot move down
            board.addPiece(fallingPiece);
            fallingPiece = pieceGenerator.nextPiece();
            board.clearRows();
        }
        board.addPiece(fallingPiece);
    }

    void moveFallingPieceLeft() {
        board.removePiece(fallingPiece);
        fallingPiece.moveLeft(board);
        board.addPiece(fallingPiece);
    }

    void moveFallingPieceRight() {
        board.removePiece(fallingPiece);
        fallingPiece.moveRight(board);
        board.addPiece(fallingPiece);
    }
}
