package TENNIS.TENNISINFO.Season.Domain;

import TENNIS.TENNISINFO.Common.domain.SeasonRapidDTO;
import TENNIS.TENNISINFO.Tournament.Domain.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "season")
@Getter
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="SEASON_ID")
    private Long seasonId;
    @OneToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;
    @Column(name = "RAPID_SEASON_ID")
    private String rapidSeasonId;
    @Column(name="YEAR")
    private String year;
    @Column(name="TOTAL_PRIZE")
    private Long totalPrize;
    @Column(name="ROUND")
    private Long round;

    public Season(SeasonRapidDTO dto, Tournament tournament){
        this.tournament = tournament;
        this.rapidSeasonId = dto.getSeasonRapidId();
        this.year = dto.getYear();
        this.totalPrize = dto.getTotalPrizeMoney() != null? dto.getTotalPrizeMoney() : 0L;
        this.round = dto.getRound();
    }
}
