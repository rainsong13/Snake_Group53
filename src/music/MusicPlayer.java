package music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MusicPlayer {
    private AdvancedPlayer player;
    private boolean isPlaying;

    // Play an MP3 file from a URL
    public void play(String songUrl) {
        try {
            URL url = new URL(songUrl);
            InputStream is = url.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            player = new AdvancedPlayer(bis);

            // Start a new thread to play the music to prevent blocking the main thread
            Thread playThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            playThread.start();

            isPlaying = true;
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
            isPlaying = false;
        }
    }
}
