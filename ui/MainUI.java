package ui;

import game.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {

    public MainUI() {
        // 设置主界面标题和大小
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);  // 使用绝对布局来简化界面

        // 设置背景图片
        JLabel background = new JLabel(new ImageIcon("path/to/your/background.gif"));
        background.setBounds(0, 0, 720, 720);
        add(background);

        // 创建按钮并加载图像
        JButton startGameButton = new JButton(new ImageIcon("path/to/your/start_button.png"));
        JButton exitButton = new JButton(new ImageIcon("path/to/your/exit_button.png"));
        JButton shopButton = new JButton(new ImageIcon("path/to/your/shop_button.png"));

        // 设置按钮的位置和大小
        startGameButton.setBounds(260, 200, 200, 50);
        shopButton.setBounds(260, 300, 200, 50);
        exitButton.setBounds(260, 400, 200, 50);

        // 添加按钮到背景面板
        background.add(startGameButton);
        background.add(shopButton);
        background.add(exitButton);

        // 设置按钮的事件监听
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击开始游戏按钮时，打开游戏界面
                Board board = new Board(20, 20);  // 创建 20x20 的棋盘
                RenderUI renderUI = new RenderUI(board);
                renderUI.setVisible(true);
                dispose();  // 关闭主菜单窗口
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击退出按钮时，关闭程序
                System.exit(0);
            }
        });
    }
}
