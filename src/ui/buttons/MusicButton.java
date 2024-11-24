package ui.buttons;

import javax.swing.*;
import music.MusicUI;

public class MusicButton extends Button {
    public MusicButton() {
        super("/ui/image/store_button.png", 100, 100);
        addActionListener(e -> {
            // Open the music UI
            MusicUI musicUI = new MusicUI();
            musicUI.setVisible(true);
        });
    }
}
