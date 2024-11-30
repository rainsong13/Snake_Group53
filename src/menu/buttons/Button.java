package menu.buttons;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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