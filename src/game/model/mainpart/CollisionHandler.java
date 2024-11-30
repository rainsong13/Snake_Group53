package game.model.mainpart;

import game.model.board.Board;
import game.model.npc.apple_pack.Apple;
import game.model.npc.apple_pack.AppleGenerator;
import game.model.npc.snake_pack.Head;
import game.model.board.Board;
import java.util.List;

public class CollisionHandler {
    private Board board;
    private Head head;
    private AppleGenerator appleGenerator;

    public CollisionHandler(Board board, Head head, AppleGenerator appleGenerator) {
        this.board = board;
        this.head = head;
        this.appleGenerator = appleGenerator;
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
