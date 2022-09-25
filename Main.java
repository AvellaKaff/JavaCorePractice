package main.java.lesson6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?lat=59.938955&lon=30.315644&lang=ru_RU&limit=5&hours=false&extra=false"))
               .header("accept", "application/json")
               .header("X-Yandex-API-Key", "469cad63-0c6a-4d2a-acc6-d52f5626e9a6")
               .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body() );
    }
}
