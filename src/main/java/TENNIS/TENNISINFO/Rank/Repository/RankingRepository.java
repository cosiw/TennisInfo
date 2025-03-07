package TENNIS.TENNISINFO.Rank.Repository;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Optional<Ranking> findByPlayer(Player player);

    @Query("SELECT r.curRanking, r.rankingId, p.playerId, p.playerName, r.curPoints, r.preRanking, r.bestRanking " +
            "FROM Ranking r " +
            "JOIN r.player p " +
            "WHERE r.curRanking IS NOT NULL")
    List<Object> findRankingDetails();
}
