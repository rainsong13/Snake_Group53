package game;

import game.mainpart.GameLoop;
import game.mainpart.CollisionHandler;
import game.mainpart.GameOverHandler;
import game.mainpart.InputHandler;
import game.npc.apple_pack.Apple;
import game.npc.apple_pack.AppleGenerator;
import game.npc.snake_pack.Head;
import game.render.RenderPanel;
import game.score.Score;  // 引入Score类
import src.game.board.Board;

import javax.swing.*;
import java.util.List;

public class GameMain {
    private Board board;
    private Head head;
    private RenderPanel renderPanel;
    private InputHandler inputHandler;
    private AppleGenerator appleGenerator;
    private GameLoop gameLoop;
    private CollisionHandler collisionHandler;
    private GameOverHandler gameOverHandler;
    private JFrame gameFrame;

    public GameMain() {
        startNewGame();
    }

    public void startNewGame() {
        // 重置分数
        Score.getInstance().resetPoints();

        // Initialize game components
        board = new Board(20, 20);
        head = new Head(10, 10);
        appleGenerator = new AppleGenerator(3);  // Instantiate AppleGenerator
        List<Apple> apples = appleGenerator.getApples();

        // Create RenderPanel
        renderPanel = new RenderPanel(board, head, apples);

        // Add input handler to the panel
        inputHandler = new InputHandler(renderPanel);  // Pass the renderPanel to InputHandler

        // Create the game window (JFrame)
        if (gameFrame == null) {
            gameFrame = new JFrame("Snake");
            gameFrame.setSize(1080, 1080);
            gameFrame.setLocationRelativeTo(null);  // Center the game window on the screen
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.add(renderPanel);
            gameFrame.setVisible(true);

            // Set focus explicitly after the frame opens
            gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowOpened(java.awt.event.WindowEvent e) {
                    renderPanel.requestFocusInWindow();
                }
            });
        } else {
            gameFrame.getContentPane().removeAll();
            gameFrame.add(renderPanel);
            gameFrame.revalidate();
            gameFrame.repaint();
        }

        // Initialize handlers
        collisionHandler = new CollisionHandler(board, head, appleGenerator);
        gameOverHandler = new GameOverHandler(renderPanel, this);

        // Start the game loop
        gameLoop = new GameLoop(100, this::updateGame);
        gameLoop.start();
    }

    public void closeGameFrame() {
        gameFrame.dispose();
    }

    private void updateGame() {
        // Update the snake's position using direction from input handler
        int directionX = inputHandler.getDirectionX();
        int directionY = inputHandler.getDirectionY();
        head.move(directionX, directionY);

        // Pass the direction to RenderPanel for rendering
        renderPanel.updateDirection(directionX, directionY);

        // Check for collisions
        if (collisionHandler.checkCollisions()) {
            gameLoop.stop();
            gameOverHandler.handleGameOver();
        }

        // Repaint the game screen
        renderPanel.repaint();
    }
}
