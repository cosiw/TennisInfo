package TENNIS.TENNISINFO.Player.Repository;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
