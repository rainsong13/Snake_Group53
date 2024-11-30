package game.output.render;

import game.model.npc.apple_pack.Apple;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppleRenderer {
    private static final Map<Color, ImageIcon> appleImages = new HashMap<>();
    private static final int CELL_SIZE = 50; // 每个单元格固定为 54x54 像素

    // Static block to加载苹果的图片资源
    static {
        try {
            appleImages.put(Color.RED, new ImageIcon("src/game/render/image/apple/redapple.png"));
            appleImages.put(Color.YELLOW, new ImageIcon("src/game/render/image/apple/yellowapple.png"));
            appleImages.put(Color.GREEN, new ImageIcon("src/game/render/image/apple/greenapple.png"));
        } catch (Exception e) {
            System.err.println("Failed to load apple images: " + e.getMessage());
        }
    }

    public void drawApples(Graphics g, List<Apple> apples) {
        for (Apple apple : apples) {
            int[] position = apple.getPosition();
            ImageIcon appleImage = appleImages.get(apple.getColor());

            if (appleImage != null) {
                // 绘制苹果图片，位置是 position[0] * CELL_SIZE, position[1] * CELL_SIZE
                g.drawImage(
                        appleImage.getImage(),
                        position[0] * CELL_SIZE, position[1] * CELL_SIZE,
                        CELL_SIZE, CELL_SIZE,  // 使用固定的单元格大小
                        null
                );
            } else {
                // 如果图片没有加载成功，使用一个颜色表示
                g.setColor(apple.getColor());
                g.fillOval(position[0] * CELL_SIZE, position[1] * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
