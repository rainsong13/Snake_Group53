package ui.buttons;

import javax.swing.*;
import game.StartGame;

public class StartButton extends Button {

    public StartButton() {
        super("/ui/image/start_button.png", 150, 150);
        addActionListener(e -> {
            // Start the game using the static start method
            StartGame.start();

            // Close the current UI window
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        });
    }
}
