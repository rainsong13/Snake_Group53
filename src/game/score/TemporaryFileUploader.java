package game.score;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TemporaryFileUploader {

    private static final String API_URL = "https://file.io";
    private static final String API_KEY = "Y7Q45BD.2KBG4H7-RW84E4T-Q7BY71C-9BCJ15M";  // 你的 API key

    public static void main(String[] args) {
        File fileToUpload = new File("src/game/score/ScoreRecord.txt");  // 请替换为你希望上传的文件路径

        try {
            String fileKey = uploadFileToAPI(fileToUpload);
            if (fileKey != null) {
                System.out.println("File uploaded successfully! File Key: " + fileKey);
            } else {
                System.out.println("Failed to upload file.");
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
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---boundary");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);  // 使用 Bearer 令牌进行授权

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes("-----boundary\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            outputStream.writeBytes("Content-Type: text/plain\r\n\r\n");

            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            outputStream.writeBytes("\r\n-----boundary--\r\n");
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                String responseBody = response.toString();
                System.out.println("Response Body: " + responseBody);  // 打印返回的 JSON 响应，方便调试

                if (responseBody.contains("\"key\":")) {
                    return responseBody.split("\"key\":\"")[1].split("\"")[0].trim();
                }
            }
        } else {
            System.err.println("Failed to upload file. Response code: " + responseCode);
        }
        return null;
    }
}
