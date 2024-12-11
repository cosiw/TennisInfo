package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.RankingApiDTO;
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

    public List<RankingApiDTO> atpRankings() throws Exception{
        List<RankingApiDTO> list = new ArrayList<>();
        String path = "tennis/rankings/atp";

        String jsonString = rapidApiConfig.sendTennisApi(path);

        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode dataArrayNode = rootNode.path("rankings");
        JsonNode updateTime = rootNode.path("updatedAtTimestamp");
        Long updateDate = updateTime.asLong();
        for(JsonNode dataNode : dataArrayNode){
            RankingApiDTO rankingApiDTO = objectMapper.treeToValue(dataNode, RankingApiDTO.class);
            rankingApiDTO.setUpdateTime(updateDate);
            list.add(rankingApiDTO);
        }
        return list;
    }
}
