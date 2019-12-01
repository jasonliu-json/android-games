package uoft.csc207.gameapplication.Games.MazeGame.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class MazePresenter {

    List<DrawingBlock> wallBlocks = new ArrayList<>();

    List<DrawingBlock> characterBlocks = new ArrayList<>();

    private int xCord = 0;
    private int yCord = 0;
    private boolean initalized = false;


    private int screenWidth;
    private int screenHeight;

    public MazePresenter(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void initialize(Character[][] maze, int blockHeight, int blockWidth) {
        while (yCord < maze[0].length) {
            while (xCord < maze.length) {

                if (maze[xCord][yCord].equals('W')) {

                    Paint paint1 = new Paint();
                    paint1.setColor(Color.rgb(180, 180, 180));
                    paint1.setStrokeWidth(1);
                    paint1.setStyle(Paint.Style.FILL);

                    Paint paint2 = new Paint();
                    paint2.setColor(Color.rgb(64, 64, 64));
                    paint2.setStrokeWidth(1);
                    paint2.setStyle(Paint.Style.FILL);

                    Paint paint3 = new Paint();
                    paint3.setColor(Color.rgb(20, 20, 20));
                    paint3.setStyle(Paint.Style.FILL);
                    paint3.setStrokeWidth(1);

                    DrawingBlock block = new DrawingBlock(xCord, yCord, blockWidth);
                    block.setPaints(paint1, paint2, paint3);

                    wallBlocks.add(block);

                    ++xCord;
                    return;

                } else if (maze[xCord][yCord].equals('E')) {
                    Paint paint1 = new Paint();
                    paint1.setColor(Color.rgb(255, 204, 204));
                    paint1.setStrokeWidth(1);
                    paint1.setStyle(Paint.Style.FILL);

                    Paint paint2 = new Paint();
                    paint2.setColor(Color.rgb(255, 102, 102));
                    paint2.setStrokeWidth(1);
                    paint2.setStyle(Paint.Style.FILL);

                    Paint paint3 = new Paint();
                    paint3.setColor(Color.rgb(240, 0, 0));
                    paint3.setStyle(Paint.Style.FILL);
                    paint3.setStrokeWidth(1);

                    DrawingBlock endBlock = new DrawingBlock(xCord, yCord, blockWidth);
                    endBlock.setPaints(paint1, paint2, paint3);

                    wallBlocks.add(endBlock);

                    ++xCord;
                    return;
                }
                ++xCord;
            }
            xCord = 0;
            ++yCord;
        }
        initalized = true;
    }

    /**
     * draws the maze game
     *
     * @param canvas the graphics canvas where we draw on
     */
    public void drawMap(Canvas canvas, Character[][] maze, int[] characterPos) {
        int blockWidth = screenWidth / maze.length;
        int blockHeight = screenHeight / maze[0].length;

        if (!initalized) {
            initialize(maze, blockHeight, blockWidth);
        }

        for (DrawingBlock block: wallBlocks) {
            block.draw(canvas);
        }

        drawCharacter(canvas, characterPos, blockWidth);
    }

    private void drawCharacter(Canvas canvas, int[] characterPos, int blockWidth) {
        if (initalized) {
            DrawingBlock block = getLastCharacterBlock();
            DrawingBlock newBlock = new DrawingBlock(characterPos[0], characterPos[1], blockWidth);
            if (block == null || !block.equals(newBlock)) {
                Paint paint1 = new Paint();
                paint1.setColor(Color.rgb(204, 255, 204));
                paint1.setStrokeWidth(1);
                paint1.setStyle(Paint.Style.FILL);

                Paint paint2 = new Paint();
                paint2.setColor(Color.rgb(102, 255, 102));
                paint2.setStrokeWidth(1);
                paint2.setStyle(Paint.Style.FILL);

                Paint paint3 = new Paint();
                paint3.setColor(Color.rgb(51, 255, 51));
                paint3.setStyle(Paint.Style.FILL);
                paint3.setStrokeWidth(1);

                newBlock.setPaints(paint1, paint2, paint3);

                characterBlocks.add(newBlock);
            }

            for (DrawingBlock character: characterBlocks) {
                character.draw(canvas);
            }

            deleteOldCharacters();
        }
    }

    private DrawingBlock getLastCharacterBlock() {
        int size = characterBlocks.size();
        if (size > 0) {
            return characterBlocks.get(size - 1);
        }
        return null;
    }

    private void deleteOldCharacters() {
        List<DrawingBlock> cleanedBlocks = new ArrayList<>();

        for (int i = 0; i < characterBlocks.size(); i++) {
            DrawingBlock block = characterBlocks.get(i);
            if (i == characterBlocks.size() - 1) {
                cleanedBlocks.add(block);
            }
            else if (!block.getDeleteStatus()) {
                block.deleteBlock(true);
                cleanedBlocks.add(block);
            }
        }
        characterBlocks = cleanedBlocks;
    }
}
