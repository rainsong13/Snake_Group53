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
        uploadButton = new JButton("Upload Score");
        uploadButton.addActionListener(e -> showUploadDialog());

        Object[] options = {uploadButton, "New Game", "Exit"};
        int option = JOptionPane.showOptionDialog(renderPanel, "YOU DIED\nScore: " + Score.getInstance().getPoints(), "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                options, "New Game");

        switch (option) {
            case 1: // New Game
                gameMain.closeGameFrame();
                new GameMain();
                break;
            case 2: // Exit
                System.exit(0);
                break;
        }
    }

    private void showUploadDialog() {
        // Disable the upload button to prevent multiple uploads
        uploadButton.setEnabled(false);

        // Show a new dialog for the upload process
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
                // Download the latest score file
                FileDownloader.downloadLatestFile();

                // Update the score file with the new record at the top
                File scoreFile = new File("src/game/score/ScoreRecord.txt");

                try {
                    // 先读取文件中的现有内容
                    StringBuilder contentBuilder = new StringBuilder();
                    if (scoreFile.exists()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                contentBuilder.append(line).append(System.lineSeparator());
                            }
                        }
                    }

                    String scoreRecord = playerName + "," + Score.getInstance().getPoints();
                    contentBuilder.insert(0, scoreRecord + System.lineSeparator());

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile, false))) {
                        writer.write(contentBuilder.toString());
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(renderPanel, "Failed to save score locally.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Upload the score file using FileUploader
                FileUploader.uploadScoreFile(scoreFile);
            } else {
                JOptionPane.showMessageDialog(renderPanel, "Name cannot be empty.", "Upload Error", JOptionPane.ERROR_MESSAGE);
                uploadButton.setEnabled(true); // Re-enable the upload button if name is empty
            }
        } else {
            uploadButton.setEnabled(true); // Re-enable the upload button if the user cancels
        }
    }
}
