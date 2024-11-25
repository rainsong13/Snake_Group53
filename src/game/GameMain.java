package game;

import game.mainpart.GameLoop;
import game.mainpart.CollisionHandler;
import game.mainpart.GameOverHandler;
import game.mainpart.InputHandler;
import game.npc.apple_pack.Apple;
import game.npc.apple_pack.AppleGenerator;
import game.npc.snake_pack.Head;
import game.render.RenderPanel;
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
        // Initialize game components
        board = new Board(20, 20);
        head = new Head(10, 10);
        appleGenerator = new AppleGenerator(3);  // Instantiate AppleGenerator
        List<Apple> apples = appleGenerator.getApples();
        renderPanel = new RenderPanel(board, head, apples);

        // Create the game window (JFrame)
        gameFrame = new JFrame("Snake");
        gameFrame.setSize(720, 720);
        gameFrame.setLocationRelativeTo(null);  // Center the game window on the screen
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(renderPanel);
        gameFrame.setVisible(true);

        // Add input handler to the panel
        inputHandler = new InputHandler(renderPanel);

        // Set focus explicitly after the frame opens
        gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                renderPanel.requestFocusInWindow();
            }
        });

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
        head.move(inputHandler.getDirectionX(), inputHandler.getDirectionY());

        // Check for collisions
        if (collisionHandler.checkCollisions()) {
            gameLoop.stop();
            gameOverHandler.handleGameOver();
        }

        // Repaint the game screen
        renderPanel.repaint();
    }
}