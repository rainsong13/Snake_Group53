package game.model;

import game.model.mainpart.GameLoop;
import game.model.mainpart.CollisionHandler;
import game.model.npc.snake_pack.*;
import game.model.mainpart.GameOverHandler;
import game.input.InputHandler;
import game.model.npc.apple_pack.Apple;
import game.model.npc.apple_pack.AppleGenerator;
import game.output.RenderPanel;
import game.model.score.Score;
import game.model.board.Board;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMain {
    private Board board;
    private Head head;
    private List<EnemySnake> enemySnakes;
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
        enemySnakes = new ArrayList<>();

        // Add enemy snakes of various types
        enemySnakes.add(new RandomSnake(5, 15, 15));
        enemySnakes.add(new AppleChasingSnake(5, 5, 5));
        enemySnakes.add(new PlayerChasingSnake(5, 5,10));
        // Create RenderPanel
        renderPanel = new RenderPanel(board, head, apples, enemySnakes);

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
        collisionHandler = new CollisionHandler(board, head, appleGenerator, enemySnakes);
        gameOverHandler = new GameOverHandler(renderPanel, this);

        // Start the game loop
        gameLoop = new GameLoop(100, this::updateGame);
        gameLoop.start();
    }

    public void closeGameFrame() {
        gameFrame.dispose();
    }

    private EnemySnake createRandomEnemySnake() {
        Random random = new Random();
        int snakeType = random.nextInt(3);

        int startX = random.nextInt(board.getBoard()[0].length);
        int startY = random.nextInt(board.getBoard().length);
        int initialLength = random.nextInt(5) + 3;

        if (snakeType == 0) {
            System.out.println("A RandomSnake was created!");
            return new RandomSnake(initialLength, startX, startY);
        } else if (snakeType == 1) {
            System.out.println("An AppleChasingSnake was created!");
            return new AppleChasingSnake(initialLength, startX, startY);
        }
        else {
            System.out.println("A PlayerChasingSnake was created!");
            return new PlayerChasingSnake(initialLength, startX, startY);
        }
    }

    private void updateGame() {
        // Update the snake's position using direction from input handler
        int directionX = inputHandler.getDirectionX();
        int directionY = inputHandler.getDirectionY();
        head.move(directionX, directionY);
        for (EnemySnake enemy : enemySnakes){
            enemy.move(appleGenerator.getApples(), head);
        }
        if (enemySnakes.isEmpty()) {
            EnemySnake newSnake = createRandomEnemySnake();
            enemySnakes.add(newSnake);
            System.out.println("A new enemy snake has been added!");
        }
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
