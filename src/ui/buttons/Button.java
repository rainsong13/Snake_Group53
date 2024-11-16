package ui.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import src.game.board.Board;
import ui.RenderUI;

public class Button extends JButton {

    public Button(String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
        setContentAreaFilled(false);
        //setBorderPainted(false);
        //setFocusPainted(false);
    }
}