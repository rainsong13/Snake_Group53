package game.model.npc.snake_pack;

import game.model.npc.apple_pack.Apple;
import game.model.npc.snake_pack.Head;

import java.util.List;

public class PlayerChasingSnake extends EnemySnake {
    public PlayerChasingSnake(int initialLength, int startX, int startY) {
        super(initialLength, startX, startY);
    }

    @Override
    public void move(List<Apple> apples, Head playerHead) {
        int[] enemyHead = this.getHeadPosition();

        // Move toward the player's snake head
        int directionX = Integer.compare(playerHead.getDirectionX(), enemyHead[0]);
        int directionY = Integer.compare(playerHead.getDirectionY(), enemyHead[1]);
        super.move(directionX, directionY);
    }
}
