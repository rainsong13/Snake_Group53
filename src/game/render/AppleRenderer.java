package game.render;

import game.npc.apple_pack.Apple;
import java.awt.*;
import java.util.List;

public class AppleRenderer {
    public void drawApples(Graphics g, List<Apple> apples) {
        int cellSize = 20;  // Size of each cell
        for (Apple apple : apples) {
            int[] position = apple.getPosition();
            g.setColor(apple.getColor());
            g.fillOval(position[0] * cellSize, position[1] * cellSize, cellSize, cellSize);  // Draw apple as a circle
        }
    }
}