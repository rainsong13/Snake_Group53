package game.mainpart;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class InputHandler extends KeyAdapter {
    private int directionX = 1;  // Initial movement direction (right)
    private int directionY = 0;

    public InputHandler(JPanel panel) {
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (directionY == 0) { // Prevent reversing direction
                    directionX = 0;
                    directionY = -1;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (directionY == 0) {
                    directionX = 0;
                    directionY = 1;
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (directionX == 0) {
                    directionX = -1;
                    directionY = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (directionX == 0) {
                    directionX = 1;
                    directionY = 0;
                }
                break;
        }
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }
}
