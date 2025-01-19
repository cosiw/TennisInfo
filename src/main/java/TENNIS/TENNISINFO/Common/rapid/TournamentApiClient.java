package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TournamentApiClient {
    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;

    public TournamentApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper) {
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public List<TournamentRapidDTO> LeagueDetails(List<TournamentRapidDTO> tournamentList) throws Exception {

        List<TournamentRapidDTO> list = new ArrayList<>();

        tournamentList.forEach(tournamentDTO -> {
            String path = "tennis/tournament/" + tournamentDTO.getTournamentRapidId();
            try{
                TournamentRapidDTO tournament = new TournamentRapidDTO();
                String jsonString = rapidApiConfig.sendTennisApi(path);
                JsonNode rootNode = objectMapper.readTree(jsonString);
                JsonNode tournamentNode = rootNode.path("uniqueTournament");
                objectMapper.readerForUpdating(tournament).readValue(tournamentNode);
                list.add(tournament);
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        return list;

//
//
//
//
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

//        return null;
    }
}
