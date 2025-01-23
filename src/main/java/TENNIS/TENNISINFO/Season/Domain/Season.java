package TENNIS.TENNISINFO.Season.Domain;

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
    @Column(name = "RAPID_SEASION_ID")
    private String rapidSeasonId;
    @Column(name="YEAR")
    private Long year;
    @Column(name="TOTAL_PRIZE")
    private Long totalPrize;

}
