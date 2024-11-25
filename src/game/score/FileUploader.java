package game.score;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUploader {

    private static final String API_URL = "https://file.io"; //
    private static final String API_KEY = "Y7Q45BD.2KBG4H7-RW84E4T-Q7BY71C-9BCJ15M";
    public static void uploadScoreFile(File file) {
        try {
            String fileKey = uploadFileToAPI(file);
            if (fileKey != null) {
                System.out.println("Score uploaded successfully!");
            } else {
                System.out.println("Failed to upload score.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred during file upload: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String uploadFileToAPI(File file) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---boundary");

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
             FileInputStream fileInputStream = new FileInputStream(file)) {

            outputStream.writeBytes("-----boundary\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            outputStream.writeBytes("Content-Type: text/plain\r\n\r\n");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.writeBytes("\r\n-----boundary--\r\n");
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response;
                while ((response = reader.readLine()) != null) {
                    if (response.contains("\"key\"")) {
                        int startIndex = response.indexOf(":") + 2;
                        int endIndex = response.indexOf("\"", startIndex);
                        return response.substring(startIndex, endIndex);
                    }
                }
            }
        } else {
            System.out.println("Failed to upload file. Response code: " + responseCode);
        }
        return null;
    }
}
