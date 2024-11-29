package game.render;

import game.score.Score;

import java.awt.*;

public class ScoreRenderer {
    public void drawScore(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString(String.valueOf(Score.getInstance().getPoints()), 50, 50);
    }
}