package game;

import game.npc.apple_pack.Apple;
import game.npc.apple_pack.AppleGenerator;
import game.npc.snake_pack.AppleChasingSnake;
import game.npc.snake_pack.EnemySnake;
import game.npc.snake_pack.Head;
import game.npc.snake_pack.RandomSnake;
import src.game.board.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameMain {
    private Board board;
    private Head head;
    private RenderPanel renderPanel;
    private Timer gameLoopTimer;
    private InputHandler inputHandler;
    private AppleGenerator appleGenerator;
    private List <EnemySnake> enemySnakes;

    public GameMain() {
        // Initialize game components
        board = new Board(20, 20);
        head = new Head(10, 10);
        appleGenerator = new AppleGenerator(3);  // Instantiate AppleGenerator
        List<Apple> apples = appleGenerator.getApples();
        enemySnakes = List.of(new RandomSnake(5, 15, 15),
                new AppleChasingSnake(5, 5, 5));
        renderPanel = new RenderPanel(board, head, apples, enemySnakes);

        // Create the game window (JFrame)
        JFrame gameFrame = new JFrame("Snake");
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

        // Start the game loop
        startGameLoop(apples);
    }

    private void startGameLoop(List<Apple> apples) {
        // Timer for the main game loop
        gameLoopTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the snake's position using direction from input handler
                head.move(inputHandler.getDirectionX(), inputHandler.getDirectionY());
                for (EnemySnake enemySnake : enemySnakes){
                    enemySnake.move(apples);
                }
                // Check for collisions (e.g., with the boundary or apples)
                checkCollisions();

                // Repaint the game screen
                renderPanel.repaint();
            }
        });
        gameLoopTimer.start();
    }

    private void checkCollisions() {
        int[] headPosition = head.getHeadPosition();
        int boardWidth = board.getBoard()[0].length;
        int boardHeight = board.getBoard().length;

        // Check for boundary collisions
        if (headPosition[0] < 0 || headPosition[1] < 0 || headPosition[0] >= boardWidth || headPosition[1] >= boardHeight) {
            gameOver();
        }

        // Check for collisions with apples
        for (Apple apple : appleGenerator.getApples()) {
            int[] applePosition = apple.getPosition();
            if (headPosition[0] == applePosition[0] && headPosition[1] == applePosition[1]) {
                apple.applyEffect(head);
                appleGenerator.getApples().remove(apple);  // Remove apple after it's eaten
                break;
            }
        }
        for (EnemySnake enemySnake : enemySnakes) {
            for (int[] part : enemySnake.getBodyParts()) {
                if (headPosition[0] == part[0] && headPosition[1] == part[1]) {
                    System.out.println("Player collided with an enemy snake! Game Over!");
                    gameOver();
                }
            }

            int[] enemyHeadPosition = enemySnake.getHeadPosition();
            for (int i = 1; i < enemySnake.getBodyParts().size(); i++) {
                int[] bodyPart = enemySnake.getBodyParts().get(i);
                if (enemyHeadPosition[0] == bodyPart[0] && enemyHeadPosition[1] == bodyPart[1]) {
                    System.out.println("Enemy snake collided with itself!");
                }
            }
        }
    }

    private void gameOver() {
        gameLoopTimer.stop();
        JOptionPane.showMessageDialog(renderPanel, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}
