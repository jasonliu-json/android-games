package uoft.csc207.gameapplication.MazeGame;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

import uoft.csc207.gameapplication.Login;


public class MazeGame {
    public Character[][] maze;
//    private Character[][] maze;
    private int xCharacter;
    private int yCharacter;
    private int xEndPos = 0;
    private int yEndPos = 0;

    private int currentLevel;

    private int mazeWidth;
    private int mazeHeight;

    private Context gameActivityContext;

    private boolean gameIsOver;

    public MazeGame(Context context) {
        gameActivityContext = context;
        mazeWidth = 2 * 7 + 1;
        mazeHeight = 2 * 13 + 1;

        this.maze = generateMaze(mazeWidth, mazeHeight);

        xCharacter = 1;
        yCharacter = 1;

        currentLevel = 1;
        gameIsOver = false;
    }

    void moveDown() {

        if (!maze[xCharacter][yCharacter + 1].equals('W')) {
            maze[xCharacter][yCharacter + 1] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            yCharacter += 1;
            checkEndpointReached();
        }
    }

    void moveUp() {
        if (!maze[xCharacter][yCharacter - 1].equals('W')) {
            maze[xCharacter][yCharacter - 1] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            yCharacter -= 1;
            checkEndpointReached();
        }
    }

    void moveLeft() {
        if (!maze[xCharacter - 1][yCharacter].equals('W')) {
            maze[xCharacter - 1][yCharacter] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            xCharacter -= 1;
            checkEndpointReached();
        }
    }

    void moveRight() {
        if (!maze[xCharacter + 1][yCharacter].equals('W')) {
            maze[xCharacter + 1][yCharacter] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            xCharacter += 1;
            checkEndpointReached();
        }
    }

    boolean getGameIsOver() {
        return gameIsOver;
    }

    private void checkEndpointReached() {
        if (xCharacter == xEndPos && yCharacter == yEndPos) {
            System.out.println("goal reached");
            xEndPos = 0;
            yEndPos = 0;
            if (currentLevel == 1) {
                gameIsOver = true;
                // temporarily print game is over
                System.out.println("Game is over");
            }
            else {
                currentLevel += 1;
                xCharacter = 1;
                yCharacter = 1;
                this.maze = generateMaze(mazeWidth, mazeHeight);
            }
        }
    }
    private Character[][] generateMaze(int unitWidth, int unitHeight) {
        Character[][] blocks = new Character[unitWidth][unitHeight];
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[0].length; y++) {
                blocks[x][y] = 'W';
            }
        }
        recursiveMazeGeneration(blocks, 1, 1);
        setEnd(blocks);
        blocks[1][1] = 'S';
        return blocks;
    }

    private void setEnd(Character[][] maze) {
        for (int x = maze.length - 2; x > 0; x -= 2) {
            int yCheck = maze[0].length - 2;
            int xCheck = x;
            boolean flag = false;
            while (xCheck < maze.length - 1) {
                if (checkSurrounding(maze, xCheck, yCheck)) {
                    flag = true;
                    maze[xCheck][yCheck] = 'E';
                    this.xEndPos = xCheck;
                    this.yEndPos = yCheck;
                    break;
                }
                xCheck += 2;
                yCheck -= 2;
            }
            if (flag) {
                break;
            }
        }
    }

    private boolean checkSurrounding(Character[][] maze, int x, int y) {
        int count = 0;
        if (maze[x + 1][y].equals('W')) {
            count += 1;
        }
        if (maze[x][y + 1].equals('W')) {
            count += 1;
        }
        if (maze[x - 1][y].equals('W')) {
            count += 1;
        }
        if (maze[x][y - 1].equals('W')) {
            count += 1;
        }
        return count > 2;
    }
    private void recursiveMazeGeneration(Character[][] maze, int startX, int startY) {
        maze[startX][startY] = 'P';
        boolean flag = true;
        while (flag) {
            ArrayList<int[]> moveBranch = new ArrayList<>();
            if (possiblePath(maze, startX, startY + 2)) {
                moveBranch.add(new int[]{0, 2});
            }
            if (possiblePath(maze, startX + 2, startY)) {
                moveBranch.add(new int[]{2, 0});
            }
            if (possiblePath(maze, startX - 2, startY)) {
                moveBranch.add(new int[]{-2, 0});
            }
            if (possiblePath(maze, startX, startY - 2)) {
                moveBranch.add(new int[]{0, -2});
            }
            if (moveBranch.size() == 0) {
                flag = false;
            }
            else {
                int randomNum = new Random().nextInt(moveBranch.size());
                int dx = moveBranch.get(randomNum)[0];
                int dy = moveBranch.get(randomNum)[1];
                maze[startX + dx/2][startY + dy/2] = 'P';
                recursiveMazeGeneration(maze, startX + dx, startY + dy);
            }
        }
    }

    private boolean possiblePath(Character[][] maze, int startX, int startY) {
        if (0 < startX && startX < maze.length && 0 < startY && startY < maze[startX].length) {
            if (maze[startX][startY].equals('W')) {
                return true;
            }
        }
        return false;
    }
}
