package game.mainui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import game.score.FileDownloader;
import java.io.*;

public class MainUI extends JFrame {
    private JButton storeButton;

    public MainUI() {
        setTitle("Snake Game UI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create Store button
        storeButton = new JButton("Store");
        storeButton.addActionListener(e -> handleStoreButton());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(storeButton);

        add(panel);
    }

    private void handleStoreButton() {
        // Download the latest ScoreRecord.txt file
        FileDownloader.downloadLatestFile();

        // Read the downloaded ScoreRecord.txt
        File scoreFile = new File("src/game/score/ScoreRecord.txt");
        if (!scoreFile.exists()) {
            JOptionPane.showMessageDialog(this, "ScoreRecord.txt not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Parse the file and generate ranking
        List<ScoreEntry> scoreEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        int score;
                        try {
                            score = Integer.parseInt(parts[1].trim());
                        } catch (NumberFormatException e) {
                            continue; // Skip invalid score entries
                        }
                        scoreEntries.add(new ScoreEntry(name, score));
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to read ScoreRecord.txt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Sort the entries by score in descending order
        scoreEntries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());

        // Generate ranking of top 5 scores
        StringBuilder rankBuilder = new StringBuilder("Top 5 Scores:\n");
        for (int i = 0; i < Math.min(5, scoreEntries.size()); i++) {
            ScoreEntry entry = scoreEntries.get(i);
            rankBuilder.append((i + 1)).append(". ").append(entry.getName()).append(", Score: ").append(entry.getScore()).append("\n");
        }

        // Display the ranking
        JOptionPane.showMessageDialog(this, rankBuilder.toString(), "Score Ranking", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI mainUI = new MainUI();
            mainUI.setVisible(true);
        });
    }

    // Inner class to represent a score entry
    private static class ScoreEntry {
        private final String name;
        private final int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}
