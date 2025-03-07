package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;


public abstract class AbstractApiClient<T> {

    //protected String rapidApiKey;
    @Value("${x-rapidapi-key}")
    protected String rapidApiKey;
    @Value("${image.upload-dir}")
    protected String imageDir;

    HttpClient client = HttpClient.newHttpClient();

    protected final ObjectMapper objectMapper;
    public AbstractApiClient(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public T executeApiCall(String url, String methodName){
        T response = null;

        try{
            String apiResponse = sendTennisApi(url);
            if("".equals(apiResponse)) return null;
            response = handleResponse(apiResponse, methodName);
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
    public List<T> executeListApiCall(String url, String methodName){

        List<T> responseList = null;
        try{
            String apiResponse = sendTennisApi(url);
            if("".equals(apiResponse)) return null;
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

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri + param))
                    .header("x-rapidapi-key", rapidApiKey)
                    .header("x-rapidapi-host", "tennisapi1.p.rapidapi.com")
                    .header("Accept-Encoding", "gzip, deflate")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            //Content-Encoding 확인
            Optional<String> contentEncoding = response.headers().firstValue("Content-Encoding");
            System.out.println("Content-Encoding: " + contentEncoding.orElse("None"));

            byte[] responseBody = response.body();

            if(contentEncoding.isPresent() && "gzip".equalsIgnoreCase(contentEncoding.get())){
                // GZIP 압축 해제
                responseText = decompressGzip(responseBody);
            }else if (contentEncoding.isPresent() && "deflate".equalsIgnoreCase(contentEncoding.get())){
                responseText = decompressDeflate(responseBody);
            }
            else{
                responseText = new String(responseBody, "UTF-8");
            }

            // UTF-8로 변환
            System.out.println("요청 텍스트: " + param);
            System.out.println("응답 텍스트: " + responseText);
        }catch(Exception e){
            e.printStackTrace();
        }

        return responseText;
    }


    public static String decompressGzip(byte[] compressedData) throws IOException {
        try(GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressedData))){
            return new String(gis.readAllBytes(), "UTF-8");
        }
    }

    public static String decompressDeflate(byte[] compressedData) throws Exception{
        try(InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(compressedData), new Inflater(true))){
            return new String(iis.readAllBytes(), "UTF-8");
        }
    }
    public String transTimeStamp(String timeStamp){
        // Unix 타임스탬프 예제 (초 단위)
        long unixTimestamp = Long.parseLong(timeStamp);

        // 타임스탬프를 Instant로 변환
        Instant instant = Instant.ofEpochSecond(unixTimestamp);

        // 대한민국 표준시 (KST)로 ZonedDateTime 변환
        ZonedDateTime kstDateTime = instant.atZone(ZoneId.of("Asia/Seoul"));

        // 포맷 설정 (yyyy-MM-dd HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


        return kstDateTime.format(formatter);
    }

    public Long eurToUsd(Long eur){
        if(eur == 0L || eur == null) return 0L;
        Long usd = Math.round(eur*0.95);
        return usd;
    }

    protected abstract T handleResponse(String response, String methodName) throws Exception;

    protected abstract List<T> handleListResponse(String response, String methodName) throws Exception;

}
