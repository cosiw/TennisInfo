package TENNIS.TENNISINFO.Common.config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RapidApiConfig {

    private String rapidApiKey;

    @Value("${x-rapidapi-key}")
    public void setRapidApikey(String rapidApiKey){
        this.rapidApiKey = rapidApiKey;
    }

    public String sendTennisApi(String param) throws Exception {
        String uri = "https://tennis-api-atp-wta-itf.p.rapidapi.com/tennis/v2/";

        System.out.println("uri : " + uri+param);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri + param))
            .header("x-rapidapi-key", rapidApiKey)
            .header("x-rapidapi-host", "tennis-api-atp-wta-itf.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body().toString();
    }

    public String sendUltimateTennisApi(String param) throws Exception {
        String uri = "https://ultimate-tennis1.p.rapidapi.com/";

        System.out.println("uri : " + uri+param);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri + param))
            .header("x-rapidapi-key", rapidApiKey)
            .header("x-rapidapi-host", "ultimate-tennis1.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body().toString();
    }
}
