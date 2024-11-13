package ui.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ExitButton extends JButton {

    public ExitButton() {
        // set icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ui/image/exit_button.png")));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
        setContentAreaFilled(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
