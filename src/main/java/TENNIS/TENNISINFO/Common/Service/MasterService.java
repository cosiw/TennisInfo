package TENNIS.TENNISINFO.Common.Service;

import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;

import java.util.List;

public interface MasterService {

    void savePlayerAndRanking() throws Exception;


}
