package uoft.csc207.gameapplication.RhythmGame;

class Target {
    private int y;
    private int allowedError;

    Target(int y, int allowedError) {
        this.y = y;
        this.allowedError = allowedError;
    }

    boolean contains(int y) {
        return Math.abs(y - this.y) <= allowedError;
    }
    boolean contains(Note note) {
        return Math.abs(note.getY() - this.y) <= allowedError;
    }

    public int getY() {return y;}
    int getAllowedError(){return allowedError;}
}
