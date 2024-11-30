package game.model.mainpart;

import javax.swing.*;
import game.model.GameMain;
import game.model.score.Score;
import game.model.score.FileUploader;
import game.model.score.FileDownloader;

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
                Score.getInstance().resetPoints();
                gameMain.closeGameFrame();
                new GameMain();
                break;
            case 2: // Exit
                System.exit(0);
                break;
        }
    }

    private void showUploadDialog() {
        uploadButton.setEnabled(false);

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
                FileDownloader.downloadLatestFile();

                File scoreFile = new File("src/game/model/score/ScoreRecord.txt");

                try {
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

                    uploadButton.setEnabled(true);
                    return;
                }

                FileUploader.uploadScoreFile(scoreFile);
            } else {
                JOptionPane.showMessageDialog(renderPanel, "Name cannot be empty.", "Upload Error", JOptionPane.ERROR_MESSAGE);
                uploadButton.setEnabled(true);
            }
        } else {
            uploadButton.setEnabled(true);
        }
    }
}
