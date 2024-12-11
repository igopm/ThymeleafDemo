package org.example.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyHttpHelper {
    private List<String> urlPictures = null;

    public List<String> getUrlPictures() {
        return urlPictures;
    }

    public MyHttpHelper(String apiUrl) {
        try {
            List<String> temp = new ArrayList<>();
            // Відправка запиту на API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Читання відповіді
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            // Парсинг JSON відповіді
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode imagesArray = objectMapper.readTree(jsonResponse.toString());

            // Прохід по масиву зображень
            for (JsonNode image : imagesArray) {
                String id = image.get("id").asText();
                String downloadUrl = "https://picsum.photos/id/" + id + "/100/100";
                //String download_url = image.get("download_url").asText();
                temp.add(downloadUrl);
            }
            urlPictures = temp;
            System.out.println("urlPictures" + urlPictures.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
