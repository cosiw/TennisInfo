package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TournamentApiClient {
    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;

    public TournamentApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper) {
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public TournamentRapidDTO Categories() throws Exception {
//        TournamentRapidDTO tournament = new TournamentRapidDTO();
//        String path = "tennis/tournament/categories";
//        String jsonString = rapidApiConfig.sendTennisApi(path);
//
//        JsonNode rootNode = objectMapper.readTree(jsonString);
//        JsonNode categoriesNode = rootNode.path("categories");
//        tournament = objectMapper.treeToValue(categoriesNode, TournamentRapidDTO.class);
//        // 이름
//        JsonNode name= teamNode.path("fullName");
//        team.setPlayerName(name.asText());
//
//        // 상금
//        JsonNode prizeNode = playerNode.path("prizeTotalRaw");
//        JsonNode curNode = prizeNode.path("currency");
//
//        String cur = curNode.asText();
//
//        if(cur.equals("EUR")){
//            Long prizeCurrent = team.getPrizeCurrent();
//            Long prizeTotal = team.getPrizeTotal();
//
//            // USD로 저장
//            team.setPrizeCurrent(rapidApiConfig.eurToUsd(prizeCurrent));
//            team.setPrizeTotal(rapidApiConfig.eurToUsd(prizeTotal));
//        }

        return null;
    }
}
