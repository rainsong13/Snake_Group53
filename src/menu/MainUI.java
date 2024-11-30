package menu;

import menu.buttons.StartButton;
import menu.buttons.ExitButton;
import menu.buttons.RankButton;

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
        setLayout(new BorderLayout());

        // set background image using resource loader
        JLabel background = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/menu/image/background.gif"))));
        background.setLayout(null);
        add(background, BorderLayout.CENTER);

        // create buttons
        StartButton startGameButton = new StartButton();
        ExitButton exitButton = new ExitButton();
        RankButton rankButton = new RankButton();

        // modify buttons
        startGameButton.setBounds(242, 260, 150, 150);
        rankButton.setBounds(10, 508, 100, 100);
        exitButton.setBounds(508, 508, 100, 100);

        // add buttons to the background
        background.add(startGameButton);
        background.add(rankButton);
        background.add(exitButton);
    }
}