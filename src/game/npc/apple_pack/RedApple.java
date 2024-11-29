package game.npc.apple_pack;

import game.score.Score;
import game.npc.snake_pack.Snake;
import java.awt.Color;
import java.util.List;

public class RedApple extends Apple {
    public RedApple(List<Apple> applesList) {
        super(10000, applesList);  // Existence time in milliseconds
        this.color = Color.RED;
        this.spawnProbability = 0.5;
    }

    @Override
    public void applyEffect(Snake snake) {
        snake.grow();  // Increase snake length by 1
        Score.getInstance().addPoints(3); // Add 1 point
    }
}