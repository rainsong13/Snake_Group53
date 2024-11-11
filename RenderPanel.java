import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 800, 800);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rendering Example");
        RenderPanel panel = new RenderPanel();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}