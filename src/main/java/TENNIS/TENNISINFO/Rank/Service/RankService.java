package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingApiDTO;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.DTO.TopRankingResponseDTO;
import java.util.List;

public interface RankService {

    List<PlayerRapidDTO> getPlayerList(List<RankingApiDTO> rankingList) throws Exception;
    void savePlayerList(List<PlayerRapidDTO> playerList) throws Exception;
    void saveRanking(List<RankingApiDTO> rankingList, List<PlayerRapidDTO> playerList) throws Exception;
}
