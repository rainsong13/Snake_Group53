package game.score;

public class Score {
    private static Score instance;
    private int points;

    private Score() {
        points = 0;
    }

    public static Score getInstance() {
        if (instance == null) {
            instance = new Score();
        }
        return instance;
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
        System.out.println("1");
    }

    public int getPoints() {
        return points;
    }
}