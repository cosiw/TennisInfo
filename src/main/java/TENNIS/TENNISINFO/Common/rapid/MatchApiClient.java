package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
import TENNIS.TENNISINFO.Common.domain.MatchRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.domain.SeasonRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchApiClient extends AbstractApiClient<MatchRapidDTO>{

    public MatchApiClient(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected MatchRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (MatchRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<MatchRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<MatchRapidDTO>) method.invoke(this, response);
    }

    public List<MatchRapidDTO> leagueEventsByRound(String response) throws Exception{
        List<MatchRapidDTO> list = new ArrayList<>();

        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode eventsNode = rootNode.path("events");

        for(JsonNode dataNode : eventsNode){
            MatchRapidDTO rankingApiDTO = objectMapper.treeToValue(dataNode, MatchRapidDTO.class);
            list.add(rankingApiDTO);
        }

        return list;
    }
}
