package game.score;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDeleter {

    private static final String API_KEY = "Y7Q45BD.2KBG4H7-RW84E4T-Q7BY71C-9BCJ15M";  // 您的 API key
    private static final String API_URL_LIST_FILES = "https://file.io/";
    private static final String API_URL_DELETE_FILE = "https://file.io/";

    public static void deleteAllFiles() {
        try {
            getAllFileKeysAndDelete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getAllFileKeysAndDelete() throws Exception {
        URL url = new URL(API_URL_LIST_FILES);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response;
                while ((response = reader.readLine()) != null) {
                    extractAndDeleteKeys(response);
                }
            }
        } else {
            System.out.println("Failed to retrieve file list. Response code: " + responseCode);
        }
    }

    private static void extractAndDeleteKeys(String response) throws Exception {
        int index = 0;
        while ((index = response.indexOf("\"key\"", index)) != -1) {
            int start = response.indexOf("\"", index + 5) + 1; // Find the start of the key value
            int end = response.indexOf("\"", start); // Find the end of the key value
            if (start > 0 && end > start) {
                String key = response.substring(start, end);
                boolean success = deleteFile(key);
                if (success) {
                    System.out.println("Deleted file with key: " + key);
                } else {
                    System.out.println("Failed to delete file with key: " + key);
                }
            }
            index = end;
        }
    }

    private static boolean deleteFile(String key) throws Exception {
        URL url = new URL(API_URL_DELETE_FILE + key);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        int responseCode = connection.getResponseCode();
        return responseCode == 200;
    }

    public static void main(String[] args) {
        deleteAllFiles();
    }
}
