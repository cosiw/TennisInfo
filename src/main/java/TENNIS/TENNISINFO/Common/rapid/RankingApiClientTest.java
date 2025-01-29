package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RankingApiClientTest extends AbstractApiClient<RankingRapidDTO>{
    public RankingApiClientTest(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected RankingRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (RankingRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<RankingRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<RankingRapidDTO>) method.invoke(this, response);
    }

    public List<RankingRapidDTO> atpRankings(String response) throws Exception{

        List<RankingRapidDTO> list = new ArrayList<>();

        JsonNode rootNode = objectMapper.readTree(response);
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
