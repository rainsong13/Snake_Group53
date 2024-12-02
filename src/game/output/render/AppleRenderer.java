package game.output.render;

import game.model.npc.apple_pack.Apple;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppleRenderer {
    private static final Map<Color, ImageIcon> appleImages = new HashMap<>();
    private static final int CELL_SIZE = 50; // to set each unit square to 54 x 54 pixels

    // Static block to load the apple file
    static {
        try {
            appleImages.put(Color.RED, new ImageIcon("src/game/output/image/apple/greenapple.png"));
            appleImages.put(Color.YELLOW, new ImageIcon("src/game/output/image/apple/redapple.png"));
            appleImages.put(Color.GREEN, new ImageIcon("src/game/output/image/apple/yellowapple.png"));
        } catch (Exception e) {
            System.err.println("Failed to load apple images: " + e.getMessage());
        }
    }

    public void drawApples(Graphics g, List<Apple> apples) {
        for (Apple apple : apples) {
            int[] position = apple.getPosition();
            ImageIcon appleImage = appleImages.get(apple.getColor());

            if (appleImage != null) {
                // render apple picture, where its position is position[0] * CELL_SIZE, position[1] * CELL_SIZE
                g.drawImage(
                        appleImage.getImage(),
                        position[0] * CELL_SIZE, position[1] * CELL_SIZE,
                        CELL_SIZE, CELL_SIZE,  // use the stable unit cube
                        null
                );
            } else {
                // if the picture failed to load, then represent it using another color
                g.setColor(apple.getColor());
                g.fillOval(position[0] * CELL_SIZE, position[1] * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
