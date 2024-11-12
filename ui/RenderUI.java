package ui;

import javax.swing.*;
import java.awt.*;

class RenderUI extends JFrame {

    public RenderUI(Board board) {

        // 设置游戏界面标题和大小
        setTitle("Snake Game");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建渲染面板并添加到框架
        RenderPanel renderPanel = new RenderPanel(board);
        add(renderPanel);
    }
}

class RenderPanel extends JPanel {
    private Board board;

    public RenderPanel(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        char[][] boardData = board.getBoard();
        int cellSize = 720 / boardData.length;  // 计算每个格子的大小

        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                // 设置颜色，空白位置为白色
                if (boardData[i][j] == '.') {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GREEN);  // 例如蛇的位置可以用绿色表示
                }
                // 绘制矩形格子
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                // 绘制边框
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}