package game.render;

import game.score.Score;
import java.awt.*;

public class ScoreRenderer {
    public void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + Score.getInstance().getPoints(), 50, 50);
    }
}
