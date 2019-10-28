package uoft.csc207.gameapplication.RhythmGame;

/**
 * A shape where (0, 0) is the top left corner, and is defined by unit lengths
 */
public class Tetromino {

    private int[][] coords;
    private int height;
    private int width;

    public Tetromino(int[][] coords) {
        this.coords = new int[4][2];
        if (validCoordinates(coords)) {
            setCoords(coords);
        } else {
            for (int i = 0; i < 4; i++) {
                this.coords[i][0] = i;
                this.coords[i][1] = 0;
            }
            height = 1;
            width = 4;
        }
    }

//    public Tetromino(Tetromino other) {
//        coords = new int[4][2];
//        setCoords(other.coords);
//
//        this.width = other.width;
//        this.height = other.height;
//    }

    public int[][] getCoords() {
        return coords;
    }

    public boolean setCoords(int[][] coords) {
        if (validCoordinates(coords)) {
            for (int i = 0; i < 4; i++) {
                int x = coords[i][0];
                int y = coords[i][1];
                this.coords[i][0] = x;
                this.coords[i][1] = y;

                if (width < x + 1) width = x + 1;
                if (height < y + 1) height = y + 1;
            }
            return true;
        }
        return false;
    }

    private boolean validCoordinates(int[][] coords) {
        if (coords.length != 4) return false;
        for (int i = 0; i < 4; i++) {
            if (coords[i].length != 2) return false;
            int x = coords[i][0];
            int y = coords[i][1];
            if (x < 0 || y < 0) return false;
            for (int j = i; j > 0; j--) {
                int x1 = coords[j - 1][0];
                int y1 = coords[j - 1][1];
                if (Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2)) == 1.0) break;
                if (j == 1) return false;
            }
        }

        return true;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
