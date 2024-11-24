package music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicUI extends JFrame {
    private final MusicPlayer musicPlayer;

    public MusicUI() {
        setTitle("Music Player");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        musicPlayer = new MusicPlayer();

        JButton playPauseButton = new JButton("Play/Pause");
        JButton nextButton = new JButton("Next Song");
        JButton increaseVolumeButton = new JButton("Increase Volume");
        JButton decreaseVolumeButton = new JButton("Decrease Volume");

        // Add action listeners to the buttons
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.togglePause();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace the URL with a new song URL as needed
                musicPlayer.stop();
                musicPlayer.play("https://example.com/song.wav");
            }
        });

        increaseVolumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.increaseVolume();
            }
        });

        decreaseVolumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.decreaseVolume();
            }
        });

        add(playPauseButton);
        add(nextButton);
        add(increaseVolumeButton);
        add(decreaseVolumeButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MusicUI musicUI = new MusicUI();
            musicUI.setVisible(true);
        });
    }
}
