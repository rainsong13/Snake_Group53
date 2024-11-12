package ui.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.game.board.Board;
import ui.RenderUI;

public class StartButton extends JButton {

    public StartButton() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ui/image/start_button.png"));
        setIcon(icon);
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
