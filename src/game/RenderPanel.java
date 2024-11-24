package game;

import game.npc.apple_pack.Apple;
import game.npc.snake_pack.Snake;
import src.game.board.Board;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RenderPanel extends JPanel {
    private Board board;
    private Snake snake;
    private List<Apple> apples;

    public RenderPanel(Board board, Snake snake, List<Apple> apples) {
        this.board = board;
        this.snake = snake;
        this.apples = apples;
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawSnake(g);
        drawApples(g);
        drawScore(g);// Draw apples
    }

    // Draw the game board
    private void drawBoard(Graphics g) {
        char[][] boardData = board.getBoard();
        int cellSize = 20;  // Size of each cell

        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                if (boardData[i][j] == '.') {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GREEN);
                }
                // Render cell
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    // Draw the snake (head + body)
    private void drawSnake(Graphics g) {
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

    // Draw the apples
    private void drawApples(Graphics g) {
        int cellSize = 20;  // Size of each cell
        for (Apple apple : apples) {
            int[] position = apple.getPosition();
            g.setColor(apple.getColor());
            g.fillOval(position[0] * cellSize, position[1] * cellSize, cellSize, cellSize);  // Draw apple as a circle
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + Score.getInstance().getPoints(), 50, 50);
    }
}
