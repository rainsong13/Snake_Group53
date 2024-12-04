package game.model.npc.snake_pack;

import game.model.npc.apple_pack.Apple;
import game.model.npc.snake_pack.Head;

import java.util.List;

//class for enemy snake that chases players
public class PlayerChasingSnake extends EnemySnake {

    //initialize with starting length and position
    public PlayerChasingSnake(int initialLength, int startX, int startY) {
        super(initialLength, startX, startY);
    }

    //movement method for player chasing snake
    @Override
    public void move(List<Apple> apples, Head playerHead) {
        int[] enemyHead = this.getHeadPosition();

        // Move toward the player's snake head
        int directionX = Integer.compare(playerHead.getDirectionX(), enemyHead[0]);
        int directionY = Integer.compare(playerHead.getDirectionY(), enemyHead[1]);
        super.move(directionX, directionY);
    }
}
