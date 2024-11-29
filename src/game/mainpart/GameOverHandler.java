package game.mainpart;

import javax.swing.*;
import game.GameMain;
import game.score.Score;
import game.score.FileUploader;
import game.score.FileDownloader;

import java.awt.*;
import java.io.*;

public class GameOverHandler {
    private JPanel renderPanel;
    private GameMain gameMain;
    private JButton uploadButton;

    public GameOverHandler(JPanel renderPanel, GameMain gameMain) {
        this.renderPanel = renderPanel;
        this.gameMain = gameMain;
    }

    public void handleGameOver() {
        // 如果上传按钮已经存在并且被添加到了面板上，移除它以避免重复
        if (uploadButton == null) {
            uploadButton = new JButton("Upload Score");
            uploadButton.addActionListener(e -> showUploadDialog());
        }

        Object[] options = {uploadButton, "New Game", "Exit"};
        int option = JOptionPane.showOptionDialog(renderPanel, "YOU DIED\nScore: " + Score.getInstance().getPoints(), "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                options, "New Game");

        switch (option) {
            case 1: // New Game
                Score.getInstance().resetPoints();  // 重置分数
                gameMain.closeGameFrame();          // 关闭当前游戏窗口
                new GameMain();                     // 创建一个新的游戏实例
                break;
            case 2: // Exit
                System.exit(0);
                break;
        }
    }

    private void showUploadDialog() {
        // 禁用上传按钮以防止重复上传
        uploadButton.setEnabled(false);

        // 显示用于上传的对话框
        JPanel uploadPanel = new JPanel();
        uploadPanel.setLayout(new BorderLayout());
        JLabel messageLabel = new JLabel("Enter your name to upload the score:");
        JTextField nameField = new JTextField(10);
        uploadPanel.add(messageLabel, BorderLayout.NORTH);
        uploadPanel.add(nameField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(renderPanel, uploadPanel, "Upload", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                // 下载最新的分数文件
                FileDownloader.downloadLatestFile();

                // 更新分数文件并将新记录添加到文件的顶部
                File scoreFile = new File("src/game/score/ScoreRecord.txt");

                try {
                    // 读取现有内容
                    StringBuilder contentBuilder = new StringBuilder();
                    if (scoreFile.exists()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                contentBuilder.append(line).append(System.lineSeparator());
                            }
                        }
                    }

                    // 在内容的顶部添加新的分数记录
                    String scoreRecord = playerName + "," + Score.getInstance().getPoints();
                    contentBuilder.insert(0, scoreRecord + System.lineSeparator());

                    // 写入更新后的内容
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile, false))) {
                        writer.write(contentBuilder.toString());
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(renderPanel, "Failed to save score locally.", "Error", JOptionPane.ERROR_MESSAGE);
                    // 重新启用上传按钮，以便用户可以重试
                    uploadButton.setEnabled(true);
                    return;
                }

                // 上传分数文件
                FileUploader.uploadScoreFile(scoreFile);
            } else {
                JOptionPane.showMessageDialog(renderPanel, "Name cannot be empty.", "Upload Error", JOptionPane.ERROR_MESSAGE);
                uploadButton.setEnabled(true); // 重新启用上传按钮，如果名称为空
            }
        } else {
            uploadButton.setEnabled(true); // 如果用户取消上传，则重新启用上传按钮
        }
    }
}
