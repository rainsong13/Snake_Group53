package game.npc.snake_pack;

import game.npc.apple_pack.Apple;

import java.util.List;
import java.util.Random;

public class EnemySnake extends Snake{
    private final Random random = new Random();

    public EnemySnake(int initialLength, int startX, int startY) {
        super(initialLength, startX, startY);
    }

    public void move(List<Apple> apples){
        int[] directions = new int[]{-1, 0, 1, 0};
        int directionX = directions[random.nextInt(directions.length)];
        int directionY = directions[random.nextInt(directions.length)];
        if (directionX != 0){
            directionY = 0; //This is to ensure that the snake does not move diagonally
        }
        while (directionX == 0 && directionY == 0){
            directionX = directions[random.nextInt(directions.length)];
            directionY = directions[random.nextInt(directions.length)];
        }
        super.move(directionX, directionY);
    }

}
