package game.mainpart;

import javax.swing.*;

public class GameOverHandler {
    private JPanel renderPanel;

    public GameOverHandler(JPanel renderPanel) {
        this.renderPanel = renderPanel;
    }

    public void handleGameOver() {
        JOptionPane.showMessageDialog(renderPanel, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}