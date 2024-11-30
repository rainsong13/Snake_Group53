package game.output.render;

import game.model.board.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardRenderer {
    private BufferedImage boardImage;

    public BoardRenderer() {
        try {
            boardImage = ImageIO.read(new File("src/game/render/image/board.png"));
        } catch (IOException e) {
            System.err.println("Error: Unable to load board image.");
            e.printStackTrace();
        }
    }

    public void drawBoard(Graphics g, Board board) {
        if (boardImage != null) {
            g.drawImage(boardImage, 0, 0, null);
        } else {
            int cellSize = 50;

            for (int i = 0; i < board.getBoard().length; i++) {
                for (int j = 0; j < board.getBoard()[i].length; j++) {
                    if (board.getBoard()[i][j] == '.') {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.GREEN);
                    }
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
