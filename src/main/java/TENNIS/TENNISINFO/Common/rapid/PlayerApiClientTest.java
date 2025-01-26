package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerApiClientTest extends AbstractApiClient<PlayerRapidDTO>{

    public PlayerApiClientTest(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper){
        super(rapidApiConfig, objectMapper);
    }

    public PlayerRapidDTO teamDetails(String rapidId) throws Exception {
        PlayerRapidDTO team = new PlayerRapidDTO();

        String path = "tennis/team/" + rapidId;
        String params = rapidId;
        PlayerRapidDTO playerDTO = executeApiCall(path, params);
        String jsonString = rapidApiConfig.sendTennisApi(path);


    }

    public String teamImage(String rapidId) throws Exception {
        String path = "tennis/team/" + rapidId + "/image";

        String jsonString = rapidApiConfig.imageApi(path);

        return jsonString;
    }


    @Override
    protected PlayerRapidDTO handleResponse(String response) {
        return null;
    }

    @Override
    protected List<PlayerRapidDTO> handleListResponse(String response) {
        return List.of();
    }
}
