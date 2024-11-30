package menu;

import game.model.board.Board;
import javax.swing.*;
import java.awt.*;

public class RenderUI extends JFrame {

    public RenderUI(Board board) {
        // set game title and size
        setTitle("Snake Game");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // creating panel
        RenderPanel renderPanel = new RenderPanel(board);
        add(renderPanel);
    }
}

class RenderPanel extends JPanel {
    private Board board;

    public RenderPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(720, 720));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        char[][] boardData = board.getBoard();
        int cellSize = 720 / boardData.length;  // Calculate size of every cell

        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                // set color
                if (boardData[i][j] == '.') {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GREEN);
                }
                // render cell
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                // render edge
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}
