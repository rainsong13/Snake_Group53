package game.render;

import game.npc.apple_pack.Apple;
import game.npc.snake_pack.Snake;
import src.game.board.Board;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RenderPanel extends JPanel {
    private Board board;
    private Snake snake;
    private List<Apple> apples;

    public RenderPanel(Board board, Snake snake, List<Apple> apples) {
        this.board = board;
        this.snake = snake;
        this.apples = apples;
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        new BoardRenderer().drawBoard(g, board);
        new SnakeRenderer().drawSnake(g, snake);
        new AppleRenderer().drawApples(g, apples);
        new ScoreRenderer().drawScore(g);
    }
}