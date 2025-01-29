package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public abstract class AbstractApiClient<T> {

    private String rapidApiKey;

    @Value("${x-rapidapi-key}")
    public void setRapidApikey(String rapidApiKey){
        this.rapidApiKey = rapidApiKey;
    }

    protected final ObjectMapper objectMapper;

    public AbstractApiClient(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public T executeApiCall(String url, String methodName){
        T response = null;

        try{
            String apiResponse = sendTennisApi(url);

            response = handleResponse(apiResponse, methodName);
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
    public List<T> executeListApiCall(String url, String methodName){

        List<T> responseList = null;
        try{
            String apiResponse = rapidApiConfig.sendTennisApi(url);
            responseList = handleListResponse(apiResponse, methodName);
        }catch(Exception e){
            e.printStackTrace();
        }

        return responseList;
    }

    protected String sendTennisApi(String param){
        String responseText = "";
        try{
            String uri = "https://tennisapi1.p.rapidapi.com/api/";

            // HttpClient 생성
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri + param))
                    .header("x-rapidapi-key", rapidApiKey)
                    .header("x-rapidapi-host", "tennisapi1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            responseText = response.body();
//            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
//            byte[] responseBody = response.body();
//            // GZIP 압축 해제
//            GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(responseBody));
//            byte[] decompressedBytes = gzipInputStream.readAllBytes();
//            gzipInputStream.close();
//
//            // UTF-8로 변환
//            responseText = new String(decompressedBytes, StandardCharsets.UTF_8);
            System.out.println("응답 텍스트: " + responseText);
        }catch(Exception e){
            e.printStackTrace();
        }

        return responseText;
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

    public Long eurToUsd(Long eur){
        if(eur == 0L || eur == null) return 0L;
        Long usd = Math.round(eur*0.95);
        return usd;
    }

    protected abstract T handleResponse(String response, String methodName) throws Exception;

    protected abstract List<T> handleListResponse(String response, String methodName) throws Exception;

}
