package game;

import game.mainpart.GameLoop;
import game.mainpart.CollisionHandler;
import game.mainpart.GameOverHandler;
import game.mainpart.InputHandler;
import game.npc.apple_pack.Apple;
import game.npc.apple_pack.AppleGenerator;
import game.npc.snake_pack.Head;
import game.render.RenderPanel;
import src.game.board.Board;

import javax.swing.*;
import java.util.List;

public class GameMain {
    private Board board;
    private Head head;
    private RenderPanel renderPanel;
    private InputHandler inputHandler;
    private AppleGenerator appleGenerator;
    private GameLoop gameLoop;
    private CollisionHandler collisionHandler;
    private GameOverHandler gameOverHandler;
    private JFrame gameFrame;
    private Timer renderTimer;  // 新增渲染定时器

    public GameMain() {
        // 初始化游戏组件
        board = new Board(20, 20);
        head = new Head(10, 10);
        appleGenerator = new AppleGenerator(3);  // 实例化 AppleGenerator
        List<Apple> apples = appleGenerator.getApples();

        // 创建渲染面板
        renderPanel = new RenderPanel(board, head, apples);

        // 将输入处理器添加到渲染面板
        inputHandler = new InputHandler(renderPanel);  // 传递 renderPanel 给 InputHandler

        // 创建游戏窗口（JFrame）
        gameFrame = new JFrame("Snake");
        gameFrame.setSize(1080, 1080);
        gameFrame.setLocationRelativeTo(null);  // 将游戏窗口居中显示
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(renderPanel);
        gameFrame.setVisible(true);

        // 设置窗口打开时获取焦点
        gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                renderPanel.requestFocusInWindow();
            }
        });

        // 初始化处理器
        collisionHandler = new CollisionHandler(board, head, appleGenerator);
        gameOverHandler = new GameOverHandler(renderPanel, this);

        // 创建 60 FPS 的渲染循环
        renderTimer = new Timer(1000 / 60, e -> renderPanel.repaint());
        renderTimer.start();

        // 开始游戏逻辑循环
        gameLoop = new GameLoop(200, this::updateGame);
        gameLoop.start();
    }

    // 关闭游戏窗口
    public void closeGameFrame() {
        gameFrame.dispose();
        renderTimer.stop();  // 停止渲染定时器
    }

    // 更新游戏逻辑
    private void updateGame() {
        // 更新蛇的位置，根据输入处理器获取的方向
        int directionX = inputHandler.getDirectionX();
        int directionY = inputHandler.getDirectionY();
        head.move(directionX, directionY);

        // 将方向传递给渲染面板以用于渲染
        renderPanel.updateDirection(directionX, directionY);

        // 检查碰撞
        if (collisionHandler.checkCollisions()) {
            gameLoop.stop();
            renderTimer.stop();  // 停止渲染
            gameOverHandler.handleGameOver();
        }
    }

    // 主方法，启动游戏
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMain::new);
    }
}
