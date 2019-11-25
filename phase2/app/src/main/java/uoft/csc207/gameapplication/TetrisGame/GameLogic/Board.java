package uoft.csc207.gameapplication.TetrisGame.GameLogic;

import java.util.Arrays;

/**
 * A class representing the playing board in a game of Tetris.
 */
public class Board {

    /**
     * The width of this board.
     */
    private int width;

    /**
     * The height of this board.
     */
    private int height;

    /**
     * A 2D array representation of this board.
     */
    private char[][] grid;

    /**
     * Construct a new Board object.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(grid[i], '.');
        }
    }

    /**
     * Return the 2D array representation of this board.
     *
     * @return The 2D array representation of this board.
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Return the width of this board.
     *
     * @return The width of this board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return the height of this board.
     *
     * @return The height of this board..
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return true iff line n is completely full.
     *
     * @param n The index of the line to be inspected.
     * @return True if line n is completely full; false otherwise.
     */
    private boolean isFull(int n) {
        for (int x = 0; x < width; x++) {
            if (grid[n][x] == '.') {
                return false;
            }
        }
        return true;
    }

    /**
     * Clear all entries in line n and shift all entries above down by one line.
     *
     * @param n The index of the line to be cleared.
     */
    private void clearLine(int n) {
        for (int y = n; y > 0; y--) { // updates lines 1-n
            grid[y] = grid[y - 1].clone();
        }
        Arrays.fill(grid[0], '.'); // updates line 0
    }

    /**
     * Clear all lines on this board that are completely full and return the number of lines cleared.
     *
     * @return The number of lines cleared.
     */
    int clearLines() {
        int linesCleared = 0;
        for (int y = 0; y < height; y++) {
            if (isFull(y)) {
                clearLine(y);
                linesCleared += 1;
            }
        }
        return linesCleared;
    }

    /**
     * Add piece to this board.
     *
     * @param piece The piece to be added.
     */
    void addPiece(Piece piece) {
        for (int y = 0; y < piece.getSprites()[0].length; y++) {
            for (int x = 0; x < piece.getSprites()[0][0].length(); x++) {
                if (piece.getSprites()[piece.getRotation()][y].charAt(x) != '.') {
                    grid[piece.getY() + y][piece.getX() + x] =
                            piece.getSprites()[piece.getRotation()][y].charAt(x);
                }
            }
        }
    }

    /**
     * Remove piece from this board.
     *
     * @param piece The piece to be removed.
     */
    void removePiece(Piece piece) {
        for (int y = 0; y < piece.getSprites()[0].length; y++) {
            for (int x = 0; x < piece.getSprites()[0][0].length(); x++) {
                if (piece.getSprites()[piece.getRotation()][y].charAt(x) != '.') {
                    grid[piece.getY() + y][piece.getX() + x] = '.';
                }
            }
        }
    }

    /**
     * Return true iff piece can move in the desired direction.
     *
     * @param piece The piece to be moved.
     * @param adjX  The number of units to be moved in the x direction.
     * @param adjY  The number of units to be moved in the y direction.
     * @return True if the piece can be moved; false otherwise.
     */
    boolean canMove(Piece piece, int adjX, int adjY) {
        for (int y = 0; y < piece.getSprites()[0].length; y++) {
            for (int x = 0; x < piece.getSprites()[0][0].length(); x++) {
                int newX = x + adjX;
                int newY = y + adjY;
                if (piece.getSprites()[piece.getRotation()][y].charAt(x) != '.') {
                    try {
                        if (grid[piece.getY() + newY][piece.getX() + newX] != '.') {
                            return false; // move results in collision
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false; // move is out of bounds
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return true iff piece can rotate in the desired direction.
     *
     * @param piece     The piece to be rotated.
     * @param direction The direction to be rotated.
     * @return True if the piece can be rotated; false otherwise.
     */
    boolean canRotate(Piece piece, int direction) {
        for (int y = 0; y < piece.getSprites()[0].length; y++) {
            for (int x = 0; x < piece.getSprites()[0][0].length(); x++) {
                int newRotation = (piece.getRotation() + direction) % piece.getSprites().length;
                if (piece.getSprites()[newRotation][y].charAt(x) != '.') {
                    try {
                        if (grid[piece.getY() + y][piece.getX() + x] != '.') {
                            return false; // rotation results in collision
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false; // rotation is out of bounds
                    }
                }
            }
        }
        return true;
    }
}
