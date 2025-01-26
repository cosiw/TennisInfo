package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;


public abstract class AbstractApiClient<T> {
    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;

    public AbstractApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper){
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public T executeApiCall(String endPoint, String params){
        String url = buildUrl(endPoint, params);
        T response = null;

        try{
            String apiResponse = rapidApiConfig.sendTennisApi(url);

            response = handleResponse(apiResponse);
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
    public List<T> executeListApiCall(String endPoint, String params){
        String url = buildUrl(endPoint, params);
        List<T> responseList = null;
        try{
            String apiResponse = rapidApiConfig.sendTennisApi(url);
            responseList = handleListResponse(apiResponse);
        }catch(Exception e){
            e.printStackTrace();
        }

        return responseList;
    }

    private String buildUrl(String endPoint, String params){
        return endPoint + params;
    }

    protected abstract T handleResponse(String response);

    protected abstract List<T> handleListResponse(String response);

}
