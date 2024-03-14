package ui;

import com.google.gson.Gson;
import model.GameData;
import response.ListGamesResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ClientCommunicator {
    private final String serverUrl = "http://localhost:8080";
    private final Gson gson = new Gson();
//    public ArrayList<GameData> get(String urlString) {
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setReadTimeout(5000);
//            connection.setRequestMethod("GET");
//            connection.connect();
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                InputStream responseBody = connection.getInputStream();
//                ListGamesResponse listGamesResponse = gson.fromJson(responseBody.toString(), ListGamesResponse.class);
//                return listGamesResponse.games();
//            } else {
//                return null;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public String post(String urlString, Object object) {
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setReadTimeout(5000);
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            connection.connect();
//            connection.getOutputStream().write(gson.toJson(object).getBytes());
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                InputStream responseBody = connection.getInputStream();
//                return responseBody.toString();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return null;
//    }
    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }
    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }
    public <T> T makeRequest(String method, String path, Object request, Class<T> responseClass, String authToken) {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            if (authToken != null) {
                http.addRequestProperty("Authorization", authToken);
            }

            writeBody(request, http);
            http.connect();
            return readBody(http, responseClass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
