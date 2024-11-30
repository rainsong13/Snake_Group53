package game.model.npc.apple_pack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AppleGenerator {
    private List<Apple> apples;  // Existing apples on the board
    private Timer appleGenerationTimer;  // Timer for controlling apple generation delay

    public AppleGenerator(int i) {
        apples = new ArrayList<>();
        startAppleGeneration();
    }

    // Method to start apple generation timer
    private void startAppleGeneration() {
        int generationDelay = 1000; // 5 seconds delay for generating apples
        appleGenerationTimer = new Timer(generationDelay, e -> generateApples());
        appleGenerationTimer.start();
    }

    public List<Apple> getApples() {
        return apples;
    }

    // Generate apples on the board
    public void generateApples() {
        if (apples.size() < 8) {  // Limit the number of apples on the board
            // Define possible apples with appropriate arguments
            List<Apple> possibleApples = List.of(
                    new RedApple(apples),
                    new YellowApple(apples),
                    new GreenApple(apples)
            );

            // Calculate total probability
            double totalProbability = 0;
            for (Apple apple : possibleApples) {
                totalProbability += apple.spawnProbability;
            }

            // Generate a random value between 0 and totalProbability
            double randomValue = Math.random() * totalProbability;

            // Determine which apple to generate based on cumulative probability
            double cumulativeProbability = 0;
            Apple selectedApple = null;

            for (Apple apple : possibleApples) {
                cumulativeProbability += apple.spawnProbability;
                if (randomValue <= cumulativeProbability) {
                    selectedApple = apple;
                    break;
                }
            }

            // Add the generated apple to the list
            if (selectedApple != null) {
                apples.add(selectedApple);
            }
        }
    }
}
