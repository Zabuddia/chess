package ui;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpCommunicator {
    private final String serverUrl;
    public HttpCommunicator(String url) {
        serverUrl = url;
    }
    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) {
        T response = null;
        InputStream respBody = null;
        // Determine whether to read from the input stream or the error stream based on the HTTP status code.
        try {
            int responseCode = http.getResponseCode();
            if (responseCode >= 200 && responseCode < 400) {
                // Successful response
                respBody = http.getInputStream();
            } else {
                // Error response
                respBody = http.getErrorStream();
            }
            // Check if the response body exists and the response class is not null
            if (respBody != null && responseClass != null) {
                InputStreamReader reader = new InputStreamReader(respBody);
                response = new Gson().fromJson(reader, responseClass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Ensure the InputStream is closed after reading
            if (respBody != null) {
                try {
                    respBody.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
        URL url;
        HttpURLConnection http = null;
        try {
            url = (new URI(serverUrl + path)).toURL();
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (!method.equals("GET") && request != null) {
                http.setDoOutput(true);
                if (authToken != null) {
                    http.addRequestProperty("Authorization", authToken);
                }
                writeBody(request, http);
            } else if (authToken != null) {
                http.addRequestProperty("Authorization", authToken);
            }

            http.connect();
            return readBody(http, responseClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}