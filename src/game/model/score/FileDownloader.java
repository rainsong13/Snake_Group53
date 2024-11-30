package game.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {

    private static final String API_KEY = "Y7Q45BD.2KBG4H7-RW84E4T-Q7BY71C-9BCJ15M";
    private static final String URL = "https://file.io/";

    public static void downloadLatestFile() {
        try {
            // Get the list of all files (assuming only one file exists)
            String key = getLatestFileKey();
            if (key != null) {
                // Download the file using the key
                downloadFile(key);
            } else {
                System.out.println("No files available to download.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getLatestFileKey() throws Exception {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response;
                while ((response = reader.readLine()) != null) {
                    // Find the first occurrence of "key" and extract it
                    int index = response.indexOf("\"key\"");
                    if (index != -1) {
                        int start = response.indexOf("\"", index + 5) + 1;
                        int end = response.indexOf("\"", start);
                        if (start > 0 && end > start) {
                            return response.substring(start, end);
                        }
                    }
                }
            }
        } else {
            System.out.println("Failed to retrieve file list. Response code: " + responseCode);
        }
        return null;
    }

    private static void downloadFile(String key) throws Exception {
        String fileDownloadUrl = URL + key;
        HttpURLConnection connection = (HttpURLConnection) new URL(fileDownloadUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(new File("src/game/model/score/ScoreRecord.txt"))) {

                byte[] dataBuffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 4096)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }

                System.out.println("File downloaded successfully: src/game/score/ScoreRecord.txt");
            }
        } else {
            System.out.println("Failed to download file with key: " + key + ". Response code: " + responseCode);
        }
    }

    public static void main(String[] args) {
        downloadLatestFile();
    }
}
