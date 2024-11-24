package game.npc.apple_pack;

import game.Score;
import game.npc.snake_pack.Snake;
import java.awt.Color;
import java.util.List;

public class YellowApple extends Apple {
    public YellowApple(List<Apple> applesList) {
        super(12000, applesList);  // Existence time in milliseconds
        this.color = Color.YELLOW;
        this.spawnProbability = 0.3;
    }

    @Override
    public void applyEffect(Snake snake) {
    //    snake.increaseLife(1);  // Increase snake life by 1
        Score.getInstance().addPoints(-1); // Add 1 point
    }
}