package game.output.render;

import game.model.npc.snake_pack.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class SnakeRenderer {
    private Image headImage;
    private Image bodyImage;

    public SnakeRenderer() {
        loadImages();
    }

    private void loadImages() {
        // 加载蛇头和身体的图像
        headImage = new ImageIcon(getClass().getResource("/game/output/image/snake/head.png")).getImage();
        bodyImage = new ImageIcon(getClass().getResource("/game/output/image/snake/body.png")).getImage();
    }

    public void drawSnake(Graphics g, Snake snake, int directionX, int directionY) {
        int cellSize = 50;  // 每一格的大小

        // 绘制蛇的身体部分
        for (int i = 1; i < snake.getBodyParts().size(); i++) {
            int[] part = snake.getBodyParts().get(i);
            int bodyDrawX = part[0] * cellSize;
            int bodyDrawY = part[1] * cellSize;
            g.drawImage(bodyImage, bodyDrawX, bodyDrawY, cellSize, cellSize, null);
        }

        // 获取蛇头的位置
        int[] headPosition = snake.getHeadPosition();
        Graphics2D g2d = (Graphics2D) g;

        // 设置旋转变换
        AffineTransform oldTransform = g2d.getTransform();
        AffineTransform transform = new AffineTransform();


        int centerX = headPosition[0] * cellSize + cellSize / 2;
        int centerY = headPosition[1] * cellSize + cellSize / 2;

        // 根据方向旋转蛇头
        if (directionX == -1 && directionY == 0) {
            // 向右
            transform.rotate(Math.toRadians(90), centerX, centerY);
        } else if (directionX == 1 && directionY == 0) {
            // 向左
            transform.rotate(Math.toRadians(-90), centerX, centerY);
        } else if (directionX == 0 && directionY == -1) {
            // 向上
            transform.rotate(Math.toRadians(180), centerX, centerY);
        }

        g2d.setTransform(transform);

        int headDrawX = centerX - (150 / 2);  // 计算蛇头左上角X位置
        int headDrawY = centerY - (150 / 2);  // 计算蛇头左上角Y位置
        g2d.drawImage(headImage, headDrawX, headDrawY, 150, 150, null);

        g2d.setTransform(oldTransform);
    }
}
