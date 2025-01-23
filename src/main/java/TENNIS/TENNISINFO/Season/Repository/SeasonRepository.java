package TENNIS.TENNISINFO.Season.Repository;

import TENNIS.TENNISINFO.Season.Domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
