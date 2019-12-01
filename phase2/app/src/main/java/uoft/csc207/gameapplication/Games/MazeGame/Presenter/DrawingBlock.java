package uoft.csc207.gameapplication.Games.MazeGame.Presenter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DrawingBlock {
    private int xCord;
    private int yCord;
    private int sideLength;

    private int blockDrawingStage;

    private Paint stageOnePaint;
    private Paint stageTwoPaint;
    private Paint finalPaint;

    private boolean deleteBlock = false;
    private boolean deleteStatus = false;

    public DrawingBlock(int xCord, int yCord, int sideLength) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.sideLength = sideLength;
        blockDrawingStage = 0;
    }

    public void setPaints(Paint stageOnePaint, Paint stageTwoPaint, Paint finalPaint) {
        this.stageOnePaint = stageOnePaint;
        this.stageTwoPaint = stageTwoPaint;
        this.finalPaint = finalPaint;
        blockDrawingStage = 0;
    }
    public void draw(Canvas canvas) {
        if (blockDrawingStage == -1) {
            deleteStatus = true;
        }

        else if (blockDrawingStage <= 1) {
            int stageBlockWidth = sideLength / 3;
            int topX = xCord * sideLength + (sideLength - stageBlockWidth) / 2;
            int topY = yCord * sideLength + (sideLength - stageBlockWidth) / 2;
            int bottomX = topX + stageBlockWidth;
            int bottomY = topY + stageBlockWidth;
            Rect rect = new Rect(topX, topY, bottomX, bottomY);
            canvas.drawRect(rect, stageOnePaint);
            if (!deleteBlock) {
                blockDrawingStage++;
            }
        }

        else if (blockDrawingStage <= 2) {
            int stageBlockWidth = sideLength * 2 / 3;
            int topX = xCord * sideLength + (sideLength - stageBlockWidth) / 2;
            int topY = yCord * sideLength + (sideLength - stageBlockWidth) / 2;
            int bottomX = topX + stageBlockWidth;
            int bottomY = topY + stageBlockWidth;
            Rect rect = new Rect(topX, topY, bottomX, bottomY);
            canvas.drawRect(rect, stageTwoPaint);
            if (!deleteBlock) {
                blockDrawingStage++;
            }
        }

        else if (blockDrawingStage <= 3) {
            Rect rect = new Rect(xCord * sideLength, yCord * sideLength, (xCord + 1) * sideLength,
                    (yCord + 1) * sideLength);
            canvas.drawRect(rect, finalPaint);
        }

        if (deleteBlock && !deleteStatus) {
            blockDrawingStage--;
        }

    }

    public void deleteBlock(boolean enter) {
        this.deleteBlock = enter;
    }

    public boolean getDeleteStatus() {
        return deleteStatus;
    }

    public boolean equals(DrawingBlock block) {
        return this.xCord == block.xCord && this.yCord == block.yCord;
    }
}
