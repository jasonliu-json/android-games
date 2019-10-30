package uoft.csc207.gameapplication.MazeGame;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {
    private Character[][] maze;
    private int[] startingPoint;
    private int[] endPoint;
    private int width;
    private int height;

    public MazeGenerator(int width, int height) {
        this.width = 2 * width + 1;
        this.height = 2 * height + 1;
        this.startingPoint =  new int[2];
        this.endPoint = new int[2];
        this.startingPoint[0] = 1;
        this.startingPoint[1] = 1;
        this.endPoint[0] = -1;
        this.endPoint[1] = -1;
        newMaze();
    }

    public int[] getStartingPoint() {
        return startingPoint;
    }

    public int[] getEndPoint() {
        return endPoint;
    }

    public Character[][] getMaze() {
        return maze;
    }

    public void newMaze() {
        maze = generateMaze(width, height);
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

    private void setEnd(Character[][] maze) {
        for (int x = maze.length - 2; x > 0; x -= 2) {
            int yCheck = maze[0].length - 2;
            int xCheck = x;
            boolean flag = false;
            while (xCheck < maze.length - 1) {
                if (checkSurrounding(maze, xCheck, yCheck)) {
                    flag = true;
                    maze[xCheck][yCheck] = 'E';
                    endPoint[0] = xCheck;
                    endPoint[1] = yCheck;
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
}
