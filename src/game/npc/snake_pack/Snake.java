package game.npc.snake_pack;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    protected List<int[]> bodyParts; // List to store the positions of the snake's body parts

    public Snake(int initialLength, int startX, int startY) {
        bodyParts = new ArrayList<>();

        // Initialize the snake with the given length at the starting position
        for (int i = 0; i < initialLength; i++) {
            bodyParts.add(new int[]{startX - i, startY}); // Each part starts at a different x position
        }
    }

    // Method to grow the snake
    public void grow() {
        int[] tail = bodyParts.get(bodyParts.size() - 1);
        bodyParts.add(new int[]{tail[0], tail[1]}); // Add a new body part at the tail's position
        System.out.println("The snake has grown!"); // Debug message
    }

    // Getters for snake body parts
    public List<int[]> getBodyParts() {
        return bodyParts;
    }

    // Method to move the snake
    public void move(int directionX, int directionY) {
        // Move each part of the snake to the position of the previous part
        for (int i = bodyParts.size() - 1; i > 0; i--) {
            bodyParts.set(i, new int[]{bodyParts.get(i - 1)[0], bodyParts.get(i - 1)[1]});
        }

        // Update the head position
        int[] head = bodyParts.get(0);
        head[0] += directionX;
        head[1] += directionY;
    }

    // Get the position of the snake's head
    public int[] getHeadPosition() {
        return bodyParts.get(0);
    }
}
