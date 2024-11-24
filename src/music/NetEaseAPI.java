package music;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class NetEaseAPI {

    private static final String HOT_PLAYLIST_URL = "https://api.imjad.cn/cloudmusic/?type=playlist&id=3778678";

    public static String getNextSongURL() {
        try {
            URL url = new URL(HOT_PLAYLIST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // Parse JSON response
            JSONObject json = new JSONObject(content.toString());
            JSONArray tracks = json.getJSONObject("playlist").getJSONArray("tracks");

            // Randomly pick a song from the playlist
            Random random = new Random();
            int index = random.nextInt(tracks.length());
            JSONObject track = tracks.getJSONObject(index);

            return track.getString("mp3Url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
