package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Repository.PlayerRepository;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import TENNIS.TENNISINFO.Rank.Repository.RankingRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    private ObjectMapper objectMapper;

    private RankingRepository rankingRepository;

    private PlayerRepository playerRepository;

    private RapidApiConfig rapidApiConfig;
    @Autowired
    public RankServiceImpl(RankingRepository rankingRepository, PlayerRepository playerRepository, RapidApiConfig rapidApiConfig, ObjectMapper objectMapper){
        this.rankingRepository = rankingRepository;
        this.playerRepository = playerRepository;
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }
    @Override
    public String getRankingApiData() throws Exception {
        String response ="";
        String param = "live_leaderboard/500";

        response = rapidApiConfig.sendUltimateTennisApi(param);
        return response;
    }

    @Override
    public void saveRankingData(String jsonString) throws Exception {

        JsonNode rootNode = objectMapper.readTree(jsonString);

        JsonNode dataArrayNode = rootNode.path("data");

        for(JsonNode dataNode : dataArrayNode){

            RankingDTO rankDto = objectMapper.treeToValue(dataNode, RankingDTO.class);

            playerRepository.findByRapidPlayerId(rankDto.getRapidPlayerId())
                .map(entity -> {
                   Ranking rank = new Ranking(rankDto, entity);
                   return rankingRepository.save(rank);
                });

        }

    }

    @Override
    public List<RankingResponseDTO> getRankingList() throws Exception {
        List<Ranking> rankList = rankingRepository.findAll();

        return rankList.stream().map(rank -> new RankingResponseDTO(rank)).collect(Collectors.toList());
    }

    @Override
    public void deleteRankingData() throws Exception {
        rankingRepository.deleteAll();
    }
}
