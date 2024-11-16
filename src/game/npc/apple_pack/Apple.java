package game.npc.apple_pack;

import game.npc.snake_pack.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class Apple {
    protected Color color;
    public double spawnProbability;  // Probability of spawning
    protected int existenceTime;  // Time for which the apple exists
    protected int posX;
    protected int posY;
    protected Random random = new Random();
    private Timer existenceTimer;

    public Apple(int existenceTime, List<Apple> applesList) {
        this.existenceTime = existenceTime;
        setRandomPosition();

        // Start a timer to remove the apple after its existence time expires
        existenceTimer = new Timer(existenceTime, e -> remove(applesList));
        existenceTimer.setRepeats(false);
        existenceTimer.start();
    }

    // Set random position for the apple
    public void setRandomPosition() {
        this.posX = random.nextInt(20);  // Assuming a 20x20 board
        this.posY = random.nextInt(20);
    }

    // Get apple position
    public int[] getPosition() {
        return new int[]{posX, posY};
    }

    // Remove the apple from the list after its existence time expires
    private void remove(List<Apple> applesList) {
        applesList.remove(this);
    }

    // Abstract method for the effect of the apple on the snake
    public abstract void applyEffect(Snake snake);

    // Get the color of the apple
    public Color getColor() {
        return color;
    }
}
