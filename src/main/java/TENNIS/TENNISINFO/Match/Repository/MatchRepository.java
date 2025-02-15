package TENNIS.TENNISINFO.Match.Repository;


import TENNIS.TENNISINFO.Match.Domain.Match;
import TENNIS.TENNISINFO.Player.Domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByMatchRapidId(String matchRapidId);
}
