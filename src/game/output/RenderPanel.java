package game.output;

import game.model.npc.apple_pack.Apple;
import game.model.npc.snake_pack.EnemySnake;
import game.model.npc.snake_pack.Snake;
import game.output.render.AppleRenderer;
import game.output.render.ScoreRenderer;
import game.output.render.SnakeRenderer;
import game.model.board.Board;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RenderPanel extends JPanel {
    private Board board;
    private Snake snake;
    private List<Apple> apples;
    private Image boardImage;
    private int directionX;
    private int directionY;
    private final List<EnemySnake> enemySnakes;
    private final SnakeRenderer snakeRenderer;

    public RenderPanel(Board board, Snake snake, List<Apple> apples, List<EnemySnake> enemySnakes) {
        this.board = board;
        this.snake = snake;
        this.apples = apples;
        this.enemySnakes = enemySnakes;
        this.snakeRenderer = new SnakeRenderer();
        setFocusable(true);
        requestFocusInWindow();
        loadImages();
    }

    private void loadImages() {
        // Load the background board image
        boardImage = new ImageIcon(getClass().getResource("/game/output/image/board.png")).getImage();
    }

    public void updateDirection(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        new SnakeRenderer().drawSnake(g, snake, directionX, directionY);
        new AppleRenderer().drawApples(g, apples);
        new ScoreRenderer().drawScore(g);
        for (EnemySnake enemy : enemySnakes) {
            snakeRenderer.drawSnake(g, enemy, 0, 0);
        }
    }

    // Draw the game board
    private void drawBoard(Graphics g) {
        if (boardImage != null) {
            // Draw the background board image
            g.drawImage(boardImage, 0, 0, 1000, 1000, this);
        } else {
            // Fallback: Draw a simple green board if the image is not loaded
            g.setColor(new Color(34, 139, 34)); // Forest green
            g.fillRect(0, 0, 1000, 1000);
        }
    }
}
