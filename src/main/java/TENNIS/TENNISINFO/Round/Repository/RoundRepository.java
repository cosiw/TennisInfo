package TENNIS.TENNISINFO.Round.Repository;

import TENNIS.TENNISINFO.Round.Domain.Round;
import TENNIS.TENNISINFO.Season.Domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

    @Query("SELECT r FROM Round r WHERE r.rapidRoundId IN :roundRapidIds AND r.season = :season")
    List<Round> findByRapidRoundIdsAndSeason(List<String> roundRapidIds, Season season);

    Optional<Round> findByRapidRoundIdAndSlugAndSeason(String roundRapidId, String slug, Season season);
}
