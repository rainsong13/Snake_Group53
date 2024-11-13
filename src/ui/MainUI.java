package ui;

import ui.buttons.StartButton;
import ui.buttons.ExitButton;
import ui.buttons.StoreButton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainUI extends JFrame {

    public MainUI() {
        // set title and interface
        setTitle("Snake Game Main Menu");
        setSize(634, 657);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // set background image using resource loader
        JLabel background = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ui/image/background.gif"))));
        background.setBounds(0, 0, 618, 618);
        add(background);

        // create buttons
        StartButton startGameButton = new StartButton();
        ExitButton exitButton = new ExitButton();
        StoreButton shopButton = new StoreButton();

        // modify buttons
        startGameButton.setBounds(242, 260, 150, 150);
        shopButton.setBounds(10, 508, 100, 100);
        exitButton.setBounds(508, 508, 100, 100);

        // add buttons to the background
        background.add(startGameButton);
        background.add(shopButton);
        background.add(exitButton);
    }
}
