package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.DTO.TopRankingResponseDTO;
import java.util.List;

public interface RankService {
    String getRankingApiData() throws Exception;
    void saveRankingData(String jsonString) throws Exception;
    List<RankingResponseDTO> getRankingList() throws Exception;
    void deleteRankingData() throws Exception;

    List<TopRankingResponseDTO> getTopRankingList() throws Exception;
}
