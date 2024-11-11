package TENNIS.TENNISINFO.Player.Repository;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByRapidPlayerId(String rapidPlayerId);
}
