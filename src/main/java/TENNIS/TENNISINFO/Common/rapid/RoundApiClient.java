package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.domain.RoundRapidDTO;
import TENNIS.TENNISINFO.Common.domain.SeasonRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoundApiClient extends AbstractApiClient<RoundRapidDTO>{
    public RoundApiClient(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected RoundRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (RoundRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<RoundRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<RoundRapidDTO>) method.invoke(this, response);
    }

    public List<RoundRapidDTO> leagueRounds(String response) throws Exception {
        List<RoundRapidDTO> list = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode roundNode = rootNode.path("rounds");
        for (JsonNode round : roundNode){
            RoundRapidDTO roundDTO = objectMapper.treeToValue(round, RoundRapidDTO.class);
            list.add(roundDTO);
        }

        return list;
    }

}
