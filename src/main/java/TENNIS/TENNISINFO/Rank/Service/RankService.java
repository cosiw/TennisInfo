package TENNIS.TENNISINFO.Rank.Service;

public interface RankService {
    String getRankingApiData() throws Exception;
    void saveRankingData(String jsonString) throws Exception;
}
