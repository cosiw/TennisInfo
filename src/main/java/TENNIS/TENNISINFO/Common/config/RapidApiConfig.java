package TENNIS.TENNISINFO.Common.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RapidApiConfig {

    private String rapidApiKey;

    @Value("${x-rapidapi-key}")
    public void setRapidApikey(String rapidApiKey){
        this.rapidApiKey = rapidApiKey;
    }

    public String sendTennisApi(String param) throws Exception{
        String uri = "https://tennisapi1.p.rapidapi.com/api/";

        // HttpClient 생성
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + param))
                .header("x-rapidapi-key", rapidApiKey)
                .header("x-rapidapi-host", "tennisapi1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] responseBody = response.body();
        // GZIP 압축 해제
        GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(responseBody));
        byte[] decompressedBytes = gzipInputStream.readAllBytes();
        gzipInputStream.close();

        // UTF-8로 변환
        String responseText = new String(decompressedBytes, StandardCharsets.UTF_8);
        System.out.println("응답 텍스트: " + responseText);

        return responseText;
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

    public DateTimeFormatter transTimeStamp(String timeStamp){
        // Unix 타임스탬프 예제 (초 단위)
        long unixTimestamp = Long.parseLong(timeStamp);

        // 타임스탬프를 Instant로 변환
        Instant instant = Instant.ofEpochSecond(unixTimestamp);

        // 대한민국 표준시 (KST)로 ZonedDateTime 변환
        ZonedDateTime kstDateTime = instant.atZone(ZoneId.of("Asia/Seoul"));

        // 포맷 설정 (yyyy-MM-dd HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return formatter;
    }
}
