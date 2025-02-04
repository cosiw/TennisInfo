package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.domain.SeasonRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SeasonApiClient extends AbstractApiClient<SeasonRapidDTO>{

    public SeasonApiClient(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected SeasonRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (SeasonRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<SeasonRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<SeasonRapidDTO>) method.invoke(this, response);
    }

    public List<SeasonRapidDTO> leagueSeasons(String response) throws Exception{
        List<SeasonRapidDTO> list = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode seasonsNode = rootNode.path("seasons");
        for(JsonNode dataNode : seasonsNode){
            SeasonRapidDTO seasonDTO = objectMapper.treeToValue(dataNode, SeasonRapidDTO.class);
            list.add(seasonDTO);
        }

        return list;
    }

    public SeasonRapidDTO leagueSeasonInfo(String response) throws Exception{
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode infoNode = rootNode.path("info");
        SeasonRapidDTO seasonDTO = objectMapper.treeToValue(infoNode, SeasonRapidDTO.class);

        if("£".equals(seasonDTO.getCurrency()) || "EUR".equals(seasonDTO.getCurrency())){
            Long totalPrizeMoney = infoNode.get("totalPrizeMoney") != null? seasonDTO.getTotalPrizeMoney() : 0L;
            seasonDTO.setTotalPrizeMoney(eurToUsd(totalPrizeMoney));
        }
        return seasonDTO;
    }
}
