package game.mainpart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop {
    private Timer gameLoopTimer;

    public GameLoop(int delay, Runnable gameUpdate) {
        gameLoopTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameUpdate.run();
            }
        });
    }

    public void start() {
        gameLoopTimer.start();
    }

    public void stop() {
        gameLoopTimer.stop();
    }
}