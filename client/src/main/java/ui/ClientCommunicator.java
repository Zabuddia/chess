package ui;

import com.google.gson.Gson;
import model.GameData;
import response.ListGamesResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ClientCommunicator {
    private final String serverUrl = "http://localhost:8080";
    private final Gson gson = new Gson();
    public ArrayList<GameData> get(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream responseBody = connection.getInputStream();
                ListGamesResponse listGamesResponse = gson.fromJson(responseBody.toString(), ListGamesResponse.class);
                return listGamesResponse.games();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
