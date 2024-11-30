package game.model.npc.apple_pack;

import game.model.score.Score;
import game.model.npc.snake_pack.Snake;
import java.awt.Color;
import java.util.List;

public class GreenApple extends Apple {
    public GreenApple(List<Apple> applesList) {
        super(8000, applesList);  // Existence time in milliseconds
        this.color = Color.GREEN;
        this.spawnProbability = 0.2;
    }

    @Override
    public void applyEffect(Snake snake) {
    //    snake.decreaseLife(1);  // Decrease snake life by 1
        Score.getInstance().addPoints(1); // Add 1 point
    }
}