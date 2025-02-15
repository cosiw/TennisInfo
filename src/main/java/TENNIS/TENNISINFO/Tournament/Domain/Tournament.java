package TENNIS.TENNISINFO.Tournament.Domain;

import TENNIS.TENNISINFO.Category.Domain.Category;
import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import TENNIS.TENNISINFO.Player.Domain.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tournament")
@Getter
@Setter
@NoArgsConstructor
public class Tournament extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="TOURNAMENT_ID")
    private Long tournamentId;

    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "RAPID_TOURNAMENT_ID")
    private String rapidTournamentId;

    @Column(name = "MATCH_TYPE")
    private String matchType;

    @Column(name = "TOURNAMENT_NAME")
    private String tournamentName;

    @Column(name = "CITY")
    private String city;

    @Column(name="GROUND_TYPE")
    private String groundType;

    @Column(name="LOGO")
    private String logo;

    @Column(name="MOST_TITLES")
    private String mostTitles;

    @OneToOne
    @JoinColumn(name="MOST_TITLE_PLAYER_ID", nullable = true)
    private Player mostTitlePlayer;

    @OneToOne
    @JoinColumn(name="TITLE_HOLDER_ID")
    private Player titleHolder;

    @Column(name="POINTS")
    private Long points;

    public Tournament(TournamentRapidDTO dto, Category category, Player mostTitlePlayer, Player titleHolder){
        this.category = category;
        this.rapidTournamentId = dto.getTournamentRapidId();
        this.matchType = dto.getMatchType();
        this.tournamentName = dto.getTournamentName();
        this.city = dto.getCity();
        this.groundType = dto.getGroundType();
        this.mostTitles = dto.getMostTitles();
        if(mostTitlePlayer.getPlayerId() != null) this.mostTitlePlayer = mostTitlePlayer;
        if(titleHolder.getPlayerId() != null) this.titleHolder = titleHolder;
        this.points = dto.getPoints();
    }
}
