package TENNIS.TENNISINFO.Player.Repository;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByRapidPlayerId(String rapidPlayerId);

//    @Query("SELECT p FROM Player p JOIN p.ranking r JOIN p.career c WHERE r.rank <= :rank ORDER BY r.rank ASC")
//    List<Player> findPlayersByRankLessThanEqual(@Param("rank") int rank);
}
