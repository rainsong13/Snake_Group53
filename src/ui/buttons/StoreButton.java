package ui.buttons;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StoreButton extends JButton {

    public StoreButton() {
        // 设置图标并调整大小
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ui/image/store_button.png")));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        // 暂时没有实现具体功能
    }
}
