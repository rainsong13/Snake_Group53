package game.npc.snake_pack;

import game.npc.apple_pack.Apple;

import java.util.List;

public class RandomSnake extends EnemySnake{

    public RandomSnake(int initialLength, int startX, int startY) {
        super(initialLength, startX, startY);
    }

    @Override
    public void move(List<Apple> apples) {
        super.move(apples);
    }
}
