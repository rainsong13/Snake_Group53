package game.model.mainpart;

import game.model.board.Board;
import game.model.npc.apple_pack.Apple;
import game.model.npc.apple_pack.AppleGenerator;
import game.model.npc.snake_pack.EnemySnake;
import game.model.npc.snake_pack.Head;
import game.model.board.Board;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
    private Board board;
    private Head head;
    private AppleGenerator appleGenerator;
    private List<EnemySnake> enemySnakes;

    public CollisionHandler(Board board, Head head, AppleGenerator appleGenerator, List<EnemySnake> enemySnakes) {
        this.board = board;
        this.head = head;
        this.appleGenerator = appleGenerator;
        this.enemySnakes = enemySnakes;
    }

    public boolean checkCollisions() {
        int[] headPosition = head.getHeadPosition();
        int boardWidth = board.getBoard()[0].length;
        int boardHeight = board.getBoard().length;

        // Check for boundary collisions
        if (headPosition[0] < 0 || headPosition[1] < 0 || headPosition[0] >= boardWidth || headPosition[1] >= boardHeight) {
            return true; // Collision with boundary
        }

        // Check for collisions with the snake's own body
        List<int[]> bodyParts = head.getBodyParts();
        for (int i = 1; i < bodyParts.size(); i++) {  // Start from 1 to skip the head itself
            int[] part = bodyParts.get(i);
            if (headPosition[0] == part[0] && headPosition[1] == part[1]) {
                return true; // Collision with body
            }
        }

        for (EnemySnake enemySnake : new ArrayList<>(enemySnakes)) {
            for (int[] part : enemySnake.getBodyParts()) {
                if (headPosition[0] == part[0] && headPosition[1] == part[1]) {
                    System.out.println("Player collided with an enemy snake! Game Over!");
                    return true;
                }
            }

            int[] enemyHeadPosition = enemySnake.getHeadPosition();
            for (int i = 1; i < enemySnake.getBodyParts().size(); i++) {
                int[] bodyPart = enemySnake.getBodyParts().get(i);
                if (enemyHeadPosition[0] == bodyPart[0] && enemyHeadPosition[1] == bodyPart[1]) {
                    System.out.println("Enemy snake collided with itself!");
                    enemySnakes.remove(enemySnake);
                }
                if (enemyHeadPosition[0] < 0 || enemyHeadPosition[1] < 0 || enemyHeadPosition[0] >= boardWidth || enemyHeadPosition[1] >= boardHeight) {
                    enemySnakes.remove(enemySnake);
                }
            }
            List<Apple> apples = appleGenerator.getApples();
            for (Apple apple : apples) {
                int[] applePosition = apple.getPosition();
                if (enemyHeadPosition[0] == applePosition[0] && enemyHeadPosition[1] == applePosition[1]) {
                    System.out.println("Enemy Snake Grows");
                    apple.applyEffect(enemySnake);
                    apples.remove(apple);  // Remove apple after it's eaten
                    break;
                }
            }
        }

        // Check for collisions with apples
        List<Apple> apples = appleGenerator.getApples();
        for (Apple apple : apples) {
            int[] applePosition = apple.getPosition();
            if (headPosition[0] == applePosition[0] && headPosition[1] == applePosition[1]) {
                apple.applyEffect(head);
                apples.remove(apple);  // Remove apple after it's eaten
                break;
            }
        }

        return false; // No collision detected
    }
}
