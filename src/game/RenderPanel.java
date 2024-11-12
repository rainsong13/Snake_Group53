package game;

import src.game.board.Board;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    private Board board;

    public RenderPanel(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    // paint the src.game.board
    private void drawBoard(Graphics g) {
        char[][] boardData = board.getBoard();
        int cellSize = 20;  // size of every cell

        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                if (boardData[i][j] == '.') {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GREEN);
                }
                // render cell
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board(20, 20);  // creating 20*20 src.game.board
        RenderPanel renderPanel = new RenderPanel(board);  // creating src.game.RenderPanel

        // creating window
        JFrame frame = new JFrame("Snake Game Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);  // adjust size of window
        frame.add(renderPanel);
        frame.setVisible(true);
    }
}
