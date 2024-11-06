package TENNIS.TENNISINFO.Rank.Repository;

import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

}
