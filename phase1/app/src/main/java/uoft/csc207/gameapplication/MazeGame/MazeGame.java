package uoft.csc207.gameapplication.MazeGame;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MazeGame {
    public Character[][] maze;
    private int xCharacter;
    private int yCharacter;
    public MazeGame() {
        this.maze = generateMaze(2 * 10 + 1, 2 * 19 + 1);
        xCharacter = 1;
        yCharacter = 1;
    }
    public void moveDown() {

        if (!maze[xCharacter][yCharacter + 1].equals('W')) {
            maze[xCharacter][yCharacter + 1] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            yCharacter += 1;
        }
    }

    public void moveUp() {
        if (!maze[xCharacter][yCharacter - 1].equals('W')) {
            maze[xCharacter][yCharacter - 1] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            yCharacter -= 1;
        }
    }

    public void moveLeft() {
        if (!maze[xCharacter - 1][yCharacter].equals('W')) {
            maze[xCharacter - 1][yCharacter] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            xCharacter -= 1;
        }
    }

    public void moveRight() {
        if (!maze[xCharacter + 1][yCharacter].equals('W')) {
            maze[xCharacter + 1][yCharacter] = maze[xCharacter][yCharacter];
            maze[xCharacter][yCharacter] = 'P';
            xCharacter += 1;
        }
    }

    public Character[][] generateMaze(int unitWidth, int unitHeight) {
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
            int y = maze[0].length - 2;
            int tempX = x;
            boolean flag = false;
            while (tempX < maze.length - 1) {
                if (checkSurrounding(maze, tempX, y)) {
                    flag = true;
                    maze[tempX][y] = 'E';
                }
                tempX += 2;
                y -= 2;
                break;
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
