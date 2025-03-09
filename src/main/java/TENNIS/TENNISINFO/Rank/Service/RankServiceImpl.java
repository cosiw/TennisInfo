package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;

import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.rapid.PlayerApiClient;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Domain.QPlayer;
import TENNIS.TENNISINFO.Player.Repository.PlayerRepository;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.DTO.TopRankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.QRanking;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import TENNIS.TENNISINFO.Rank.Repository.RankingRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final JPAQueryFactory queryFactory;

    @Autowired
    public RankServiceImpl(JPAQueryFactory queryFactory, RankingRepository rankingRepository, PlayerRepository playerRepository, RapidApiConfig rapidApiConfig, ObjectMapper objectMapper, PlayerApiClient playerApiClient){
        this.rankingRepository = rankingRepository;
        this.playerRepository = playerRepository;
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
        this.playerApiClient = playerApiClient;
        this.queryFactory = queryFactory;
    }


    @Override
    public List<RankingResponseDTO> getRankingList() throws Exception {

        QRanking ranking= QRanking.ranking;

        BooleanExpression rankingCondition = ranking.curRank.loe(10);

        var rankList = queryFactory.selectFrom(ranking)
                .where(rankingCondition)
                .fetch();

        return rankList.stream()
                .map(rank -> new RankingResponseDTO(rank)).collect(Collectors.toList());
    }

    @Override
    public void savePlayerList(List<PlayerRapidDTO> playerList) throws Exception {

    }

    @Override
    public void saveRanking(List<RankingRapidDTO> rankingList, List<PlayerRapidDTO> playerList) throws Exception {

    }
}
