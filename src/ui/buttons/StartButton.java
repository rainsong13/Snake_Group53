package ui.buttons;

import src.game.board.Board;
import ui.RenderUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends Button {

    public StartButton() {
        super("/ui/image/start_button.png", 150, 150);
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