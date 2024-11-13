package ui.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import src.game.board.Board;
import ui.RenderUI;

public class StartButton extends JButton {

    public StartButton() {
        // set icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ui/image/start_button.png")));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
        setContentAreaFilled(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board board = new Board(20, 20);
                RenderUI renderUI = new RenderUI(board);
                renderUI.setVisible(true);
                SwingUtilities.getWindowAncestor(StartButton.this).dispose();
            }
        });
    }
}
