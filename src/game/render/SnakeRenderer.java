package game.render;

import game.npc.snake_pack.Snake;
import java.awt.*;

public class SnakeRenderer {
    public void drawSnake(Graphics g, Snake snake) {
        int cellSize = 20;  // Size of each cell

        // Draw the head
        g.setColor(Color.RED);  // Use red to represent the snake's head
        int[] headPosition = snake.getHeadPosition();
        g.fillRect(headPosition[0] * cellSize, headPosition[1] * cellSize, cellSize, cellSize);

        // Draw the body
        g.setColor(Color.GREEN);  // Use green to represent the snake's body
        for (int i = 1; i < snake.getBodyParts().size(); i++) {
            int[] part = snake.getBodyParts().get(i);
            g.fillRect(part[0] * cellSize, part[1] * cellSize, cellSize, cellSize);
        }
    }
}