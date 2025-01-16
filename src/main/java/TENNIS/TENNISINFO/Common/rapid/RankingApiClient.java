package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;

import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class RankingApiClient {

    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;
    public RankingApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper) {
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public List<RankingRapidDTO> atpRankings() throws Exception{
        List<RankingRapidDTO> list = new ArrayList<>();
        String path = "tennis/rankings/atp";

        String jsonString = rapidApiConfig.sendTennisApi(path);

        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode dataArrayNode = rootNode.path("rankings");
        JsonNode updateTime = rootNode.path("updatedAtTimestamp");
        String updateDate = updateTime.toString();
        for(JsonNode dataNode : dataArrayNode){
            RankingRapidDTO rankingApiDTO = objectMapper.treeToValue(dataNode, RankingRapidDTO.class);
            String id = dataNode.path("team").path("id").toString();
            rankingApiDTO.setUpdateTime(updateDate);
            list.add(rankingApiDTO);
        }
        return list;
    }
}
