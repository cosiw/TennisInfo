package TENNIS.TENNISINFO.Tournament.Domain;

import TENNIS.TENNISINFO.Category.Domain.Category;
import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import TENNIS.TENNISINFO.Player.Domain.Player;
import jakarta.persistence.*;

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

    @Column(name="MOST_TITLE_PLAYER_ID")
    private Player mostTitlePlayerId;

    @Column(name="TITLE_HOLDER_ID")
    private Player titleHolderId;

    @Column(name="POINTS")
    private Long points;
}
