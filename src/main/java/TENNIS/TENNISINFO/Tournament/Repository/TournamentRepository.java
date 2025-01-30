package TENNIS.TENNISINFO.Tournament.Repository;

import TENNIS.TENNISINFO.Category.Domain.Category;
import TENNIS.TENNISINFO.Tournament.Domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Optional<Tournament> findByRapidTournamentId(String rapidTournamentId);
}
