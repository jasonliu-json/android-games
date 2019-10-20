package Games;

public abstract class Game {

  // Statistics
  private int pointsGained = 0;
  private int numDeaths = 0;

  public abstract void run();

  // TODO: We could have this method implemented and pass in a list of objects to draw?
  public abstract void draw();

  public int getNumDeaths() {
    return numDeaths;
  }

  public void setNumDeaths(int numDeaths) {
    this.numDeaths = numDeaths;
  }

  public int getPointsGained() {
    return pointsGained;
  }

  public void setPointsGained(int pointsGained) {
    this.pointsGained = pointsGained;
  }
}
