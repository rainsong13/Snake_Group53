package ui;

import ui.buttons.StartButton;
import ui.buttons.ExitButton;
import ui.buttons.StoreButton;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    public MainUI() {
        // set title and interface
        setTitle("Snake Game Main Menu");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // set background image
        JLabel background = new JLabel(new ImageIcon("path/to/your/background.gif"));
        background.setBounds(0, 0, 720, 720);
        add(background);

        // creat buttons
        StartButton startGameButton = new StartButton();
        ExitButton exitButton = new ExitButton();
        StoreButton shopButton = new StoreButton();

        // modify buttons
        startGameButton.setBounds(260, 200, 200, 50);
        shopButton.setBounds(260, 300, 200, 50);
        exitButton.setBounds(260, 400, 200, 50);

        background.add(startGameButton);
        background.add(shopButton);
        background.add(exitButton);
    }
}
