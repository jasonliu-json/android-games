package uoft.csc207.gameapplication.Games.MazeGame.Controller;

public class TapController extends MazeController{
    private double acceptedError;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    private double quadrantOneAngle;
    private double[] vectorToCenterOfScreen;
    private double[] negativeCenterToScreenVector;

    public TapController(double screenWidth, double screenHeight) {
        acceptedError = 40;
        startY = -1;
        startX = -1;
        endX = -1;
        endY = -1;
        double normLength = normLength(new double[] {screenWidth, screenHeight});
        vectorToCenterOfScreen = new double[]{screenWidth / 2, screenHeight / 2};
        negativeCenterToScreenVector = new double[] {-screenWidth / normLength, -screenHeight / normLength};
        initializeQuadrants(screenWidth, screenHeight);
    }
    private void initializeQuadrants(double screenWidth, double screenHeight) {
        quadrantOneAngle = Math.atan(screenHeight/screenWidth);
    }

    private double dotProduct(double[] vector1, double[] vector2) {
        double dotSum = 0;
        for (int i = 0; i < vector1.length; i++) {
            dotSum += vector1[i] * vector2[i];
        }
        return dotSum;
    }

    private double normLength(double[] vector) {
        double nonSquaredLength = 0;
        for (int i = 0; i < vector.length; i++) {
            nonSquaredLength += Math.pow(vector[i], 2);
        }
        return Math.sqrt(nonSquaredLength);
    }

    private int findQuadrant() {
        double horizontalLength = startX - vectorToCenterOfScreen[0];
        double verticalLength = startY - vectorToCenterOfScreen[1];
        double[] tapVectorFromCenter = new double[]{horizontalLength, verticalLength};


        double dotValue = dotProduct(negativeCenterToScreenVector, tapVectorFromCenter);
        double normSum = normLength(negativeCenterToScreenVector) * normLength(tapVectorFromCenter);
        double theta = Math.acos(dotValue / normSum);

        double slope = negativeCenterToScreenVector[1] / negativeCenterToScreenVector[0];

        System.out.println(theta);
        System.out.println(quadrantOneAngle);
        System.out.println(horizontalLength * slope);
        System.out.println(verticalLength);

        if (horizontalLength * slope > verticalLength) {
            return theta < quadrantOneAngle ? 1 : 2;
        }
        else {
            return theta < 2 * quadrantOneAngle ? 4 : 3;
        }
    }

    @Override
    public int touchStart(float x, float y) {
        startX = x;
        startY = y;
        endX = x;
        endY = y;
        return -1;
    }

    @Override
    public int touchMove(float x, float y) {
        endX = x;
        endY = y;
        return -1;
    }

    @Override
    public int touchUp() {
        Double distance = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
        System.out.println(distance);
        if (distance < acceptedError) {
            System.out.println("calculate");
            int quadrant = findQuadrant();
            System.out.println("Calculation done");
            System.out.println(quadrant);
            startY = -1;
            startX = -1;
            endX = -1;
            endY = -1;
            return quadrant;
        }
        return -1;
    }
}
