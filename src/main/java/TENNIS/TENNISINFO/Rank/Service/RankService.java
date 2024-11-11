package TENNIS.TENNISINFO.Rank.Service;

import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import java.util.List;

public interface RankService {
    String getRankingApiData() throws Exception;
    void saveRankingData(String jsonString) throws Exception;
    List<RankingResponseDTO> getRankingList() throws Exception;
}
