package game.npc.snake_pack;

public class Head extends Snake {
    private int directionX = 1;  // Initial movement direction (right)
    private int directionY = 0;

    public Head(int startX, int startY) {
        super(1, startX, startY); // Start with a length of 1
    }

    // Set the direction for the head movement
    public void setDirection(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    // Method to move the head
    public void move() {
        super.move(directionX, directionY); // Use Snake's move method with the current direction
    }
}
