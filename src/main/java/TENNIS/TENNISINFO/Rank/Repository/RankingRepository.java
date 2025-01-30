package TENNIS.TENNISINFO.Rank.Repository;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Optional<Ranking> findByPlayer(Player player);
}
