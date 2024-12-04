package game.model.npc.snake_pack;

import game.model.npc.apple_pack.Apple;

import java.util.List;

//class for randomly moving snake
public class RandomSnake extends EnemySnake{

    //initialize with starting length and position
    public RandomSnake(int initialLength, int startX, int startY) {
        super(initialLength, startX, startY);
    }

    //currently the same as the default movement
    @Override
    public void move(List<Apple> apples, Head playerHead) {
        super.move(apples, playerHead);
    }
}
