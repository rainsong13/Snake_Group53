package menu.buttons;

import game.model.score.FileDownloader;
import game.model.score.FileUploader;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankButton extends Button {

    public RankButton() {
        super("/menu/image/store_button.png", 80, 80);
        addActionListener(e -> showRankings());
    }

    private void showRankings() {
        // Step 1: Download the latest ScoreRecord.txt
        FileDownloader.downloadLatestFile();
        File scoreFile = new File("src/game/model/score/ScoreRecord.txt");

        // Step 2: Read the file and store scores in a list
        List<PlayerScore> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        int score = Integer.parseInt(parts[1].trim());
                        scores.add(new PlayerScore(name, score));
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to read scores from file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 3: Sort the scores in descending order
        scores.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        // Step 4: Display the top 5 scores
        StringBuilder rankingMessage = new StringBuilder("Top 5 Scores:\n");
        for (int i = 0; i < Math.min(5, scores.size()); i++) {
            PlayerScore playerScore = scores.get(i);
            rankingMessage.append((i + 1)).append(". ").append(playerScore.getName()).append(": ").append(playerScore.getScore()).append("\n");
        }

        JOptionPane.showMessageDialog(null, rankingMessage.toString(), "Rankings", JOptionPane.INFORMATION_MESSAGE);

        // Step 5:upload
        FileUploader.uploadScoreFile(scoreFile);
    }

    // Inner class to store player name and score
    private static class PlayerScore {
        private final String name;
        private final int score;

        public PlayerScore(String name, int score) {
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
