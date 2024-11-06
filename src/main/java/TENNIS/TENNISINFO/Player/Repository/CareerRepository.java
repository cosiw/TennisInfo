package TENNIS.TENNISINFO.Player.Repository;

import TENNIS.TENNISINFO.Player.Domain.Career;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {

}
