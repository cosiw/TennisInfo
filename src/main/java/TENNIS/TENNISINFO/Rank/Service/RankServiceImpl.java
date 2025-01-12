package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;

import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.rapid.PlayerApiClient;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Repository.PlayerRepository;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.DTO.TopRankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import TENNIS.TENNISINFO.Rank.Repository.RankingRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    private ObjectMapper objectMapper;

    private RankingRepository rankingRepository;

    private PlayerRepository playerRepository;

    private RapidApiConfig rapidApiConfig;
    private PlayerApiClient playerApiClient;
    @Autowired
    public RankServiceImpl(RankingRepository rankingRepository, PlayerRepository playerRepository, RapidApiConfig rapidApiConfig, ObjectMapper objectMapper, PlayerApiClient playerApiClient){
        this.rankingRepository = rankingRepository;
        this.playerRepository = playerRepository;
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
        this.playerApiClient = playerApiClient;
    }


    @Override
    public List<PlayerRapidDTO> getPlayerList(List<RankingRapidDTO> rankingList) throws Exception {
        return rankingList.stream()
                .map(rank -> {
                    try{
                        return playerApiClient.teamDetails(rank.getTeam().getPlayerRapidId());
                    }catch(Exception e){
                        throw new RuntimeException("API 호출 실패", e);
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public void savePlayerList(List<PlayerRapidDTO> playerList) throws Exception {

    }

    @Override
    public void saveRanking(List<RankingRapidDTO> rankingList, List<PlayerRapidDTO> playerList) throws Exception {

    }
}
