package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import TENNIS.TENNISINFO.Rank.Repository.RankingRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    private ObjectMapper objectMapper;

    private RankingRepository rankingRepository;

    private RapidApiConfig rapidApiConfig;
    @Autowired
    public RankServiceImpl(RankingRepository rankingRepository, RapidApiConfig rapidApiConfig, ObjectMapper objectMapper){
        this.rankingRepository = rankingRepository;
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }
    @Override
    public String getRankingApiData() throws Exception {
        String response ="";
        String param = "live_leaderboard/500";
        System.out.println("param : " + param);
        response = rapidApiConfig.sendUltimateTennisApi(param);
        return response;
    }

    @Override
    public void saveRankingData(String jsonString) throws Exception {

        JsonNode rootNode = objectMapper.readTree(jsonString);

        JsonNode dataArrayNode = rootNode.path("data");

        List<Ranking> rankingList = new ArrayList<>();
        for(JsonNode dataNode : dataArrayNode){

            RankingDTO rankDto = objectMapper.treeToValue(dataNode, RankingDTO.class);

            Ranking rank = new Ranking(rankDto);

            rankingList.add(rank);
        }

        rankingRepository.saveAll(rankingList);
    }
}
