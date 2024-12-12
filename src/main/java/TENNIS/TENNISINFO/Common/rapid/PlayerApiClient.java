package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingApiDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerApiClient {
    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;

    public PlayerApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper) {
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public PlayerRapidDTO teamDetails(String rapidId) throws Exception {
        PlayerRapidDTO team = new PlayerRapidDTO();

        String path = "tennis/team/" + rapidId;

        String jsonString = rapidApiConfig.sendTennisApi(path);

        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode teamNode = rootNode.path("team");
        JsonNode playerNode = teamNode.path("playerTeamInfo");
        team = objectMapper.treeToValue(playerNode, PlayerRapidDTO.class);
        // 이름
        JsonNode name= teamNode.path("fullName");
        team.setPlayerName(name.asText());

        // 상금
        JsonNode prizeNode = playerNode.path("prizeTotalRaw");
        JsonNode curNode = prizeNode.path("currency");

        String cur = curNode.asText();

        if(cur.equals("EUR")){
            Long prizeCurrent = team.getPrizeCurrent();
            Long prizeTotal = team.getPrizeTotal();

            // USD로 저장
            team.setPrizeCurrent(rapidApiConfig.eurToUsd(prizeCurrent));
            team.setPrizeTotal(rapidApiConfig.eurToUsd(prizeTotal));
        }

        return team;
    }

    public String teamImage(String rapidId) throws Exception {
        String path = "tennis/team/" + rapidId + "/image";

        String jsonString = rapidApiConfig.imageApi(path);

        return jsonString;
    }


}
