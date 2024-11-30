package game.model.npc.snake_pack;

public class Body extends Snake {
    public Body(int initialLength, int startX, int startY) {
        super(initialLength, startX, startY);
    }

    // The Body class can contain specific logic related to the body of the snake,
    // such as collision detection or following the head's path.

    // Method to follow the head
    public void followHead(Head head) {
        // Move each part of the body to the position of the previous part
        for (int i = bodyParts.size() - 1; i > 0; i--) {
            bodyParts.set(i, bodyParts.get(i - 1));
        }
        // Update the first body part to follow the head
        bodyParts.set(0, new int[]{head.getHeadPosition()[0], head.getHeadPosition()[1]});
    }

}
