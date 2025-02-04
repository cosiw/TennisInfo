package TENNIS.TENNISINFO.Round.Domain;

import TENNIS.TENNISINFO.Common.domain.RoundRapidDTO;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Season.Domain.Season;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "round")
@Getter
@Setter
@NoArgsConstructor
public class Round {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ROUND_ID")
    private Long roundId;
    @OneToOne
    @JoinColumn(name = "SEASON_ID")
    private Season season;
    @Column(name = "ROUND_RAPID_ID")
    private String rapidRoundId;
    @Column(name="NAME")
    private String name;
    @Column(name="SLUG")
    private String slug;

    public Round(RoundRapidDTO dto, Season season){
        this.season = season;
        this.rapidRoundId = dto.getRoundRapidId();
        this.name = dto.getName();
        this.slug = dto.getSlug();
    }
}
