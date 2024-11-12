package TENNIS.TENNISINFO.Player.Repository;

import TENNIS.TENNISINFO.Player.Domain.Career;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
    Optional<Career> findByPlayer(Player player);
}
