package game.render;

import src.game.board.Board;
import java.awt.*;

public class BoardRenderer {
    public void drawBoard(Graphics g, Board board) {
        char[][] boardData = board.getBoard();
        int cellSize = 20;  // Size of each cell

        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                if (boardData[i][j] == '.') {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GREEN);
                }
                // Render cell
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}