package TENNIS.TENNISINFO.Match.Domain;

import TENNIS.TENNISINFO.Common.domain.MatchRapidDTO;
import TENNIS.TENNISINFO.Common.domain.ScoreDTO;
import TENNIS.TENNISINFO.Common.domain.TimeDTO;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Round.Domain.Round;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_match")
@Getter
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MATCH_ID")
    private Long matchId;
    @OneToOne
    @JoinColumn(name="ROUND_ID")
    private Round round;
    @Column(name="RAPID_MATCH_ID")
    private String matchRapidId;
    @Column(name="HOME_SEED")
    private String homeSeed;
    @Column(name="AWAY_SEED")
    private String awaySeed;
    @Column(name="HOME_SCORE")
    private Long homeScore;
    @Column(name="AWAY_SCORE")
    private Long awayScore;
    @OneToOne
    @JoinColumn(name="HOME_PLAYER", nullable = true)
    private Player homePlayer;
    @OneToOne
    @JoinColumn(name="AWAY_PLAYER", nullable = true)
    private Player awayPlayer;
    @Column(name="HOME_SET1")
    private Long homeSet1;
    @Column(name="HOME_SET2")
    private Long homeSet2;
    @Column(name="HOME_SET3")
    private Long homeSet3;
    @Column(name="HOME_SET4")
    private Long homeSet4;
    @Column(name="HOME_SET5")
    private Long homeSet5;
    @Column(name="AWAY_SET1")
    private Long awaySet1;
    @Column(name="AWAY_SET2")
    private Long awaySet2;
    @Column(name="AWAY_SET3")
    private Long awaySet3;
    @Column(name="AWAY_SET4")
    private Long awaySet4;
    @Column(name="AWAY_SET5")
    private Long awaySet5;
    @Column(name="PERIOD_SET1")
    private String periodSet1;
    @Column(name="PERIOD_SET2")
    private String periodSet2;
    @Column(name="PERIOD_SET3")
    private String periodSet3;
    @Column(name="PERIOD_SET4")
    private String periodSet4;
    @Column(name="PERIOD_SET5")
    private String periodSet5;
    @Column(name="HOME_TIE_SET1")
    private Long homeTieSet1;
    @Column(name="HOME_TIE_SET2")
    private Long homeTieSet2;
    @Column(name="HOME_TIE_SET3")
    private Long homeTieSet3;
    @Column(name="HOME_TIE_SET4")
    private Long homeTieSet4;
    @Column(name="HOME_TIE_SET5")
    private Long homeTieSet5;
    @Column(name="AWAY_TIE_SET1")
    private Long awayTieSet1;
    @Column(name="AWAY_TIE_SET2")
    private Long awayTieSet2;
    @Column(name="AWAY_TIE_SET3")
    private Long awayTieSet3;
    @Column(name="AWAY_TIE_SET4")
    private Long awayTieSet4;
    @Column(name="AWAY_TIE_SET5")
    private Long awayTieSet5;
    @Column(name="START_TIMESTAMP")
    private String startTimeStamp;
    @Column(name="WINNER")
    private String winner;

    public Match(MatchRapidDTO dto, Round round, Player homePlayer, Player awayPlayer){
        ScoreDTO homeScore = dto.getHomeScore();
        ScoreDTO awayScore = dto.getAwayScore();
        TimeDTO time = dto.getTime();
        this.matchRapidId = dto.getMatchRapidId();
        this.homeSeed = dto.getHomeSeed();
        this.awaySeed = dto.getAwaySeed();
        this.homeScore = homeScore.getCurrent();
        this.awayScore = awayScore.getCurrent();
        this.homeSet1 = homeScore.getScore1();
        this.homeSet2 = homeScore.getScore2();
        this.homeSet3 = homeScore.getScore3();
        this.homeSet4 = homeScore.getScore4();
        this.homeSet5 = homeScore.getScore5();
        this.awaySet1 = awayScore.getScore1();
        this.awaySet2 = awayScore.getScore2();
        this.awaySet3 = awayScore.getScore3();
        this.awaySet4 = awayScore.getScore4();
        this.awaySet5 = awayScore.getScore5();
        this.homeTieSet1 = homeScore.getTieBreak1();
        this.homeTieSet2 = homeScore.getTieBreak2();
        this.homeTieSet3 = homeScore.getTieBreak3();
        this.homeTieSet4 = homeScore.getTieBreak4();
        this.homeTieSet5 = homeScore.getTieBreak5();
        this.awayTieSet1 = awayScore.getTieBreak1();
        this.awayTieSet2 = awayScore.getTieBreak2();
        this.awayTieSet3 = awayScore.getTieBreak3();
        this.awayTieSet4 = awayScore.getTieBreak4();
        this.awayTieSet5 = awayScore.getTieBreak5();
        this.periodSet1 = time.getTime1();
        this.periodSet2 = time.getTime2();
        this.periodSet3 = time.getTime3();
        this.periodSet4 = time.getTime4();
        this.periodSet5 = time.getTime5();
        this.round = round;
        this.homePlayer = homePlayer;
        this.awayPlayer = awayPlayer;
        this.startTimeStamp = dto.getStartTimestamp();
        this.winner = dto.getWinnerCode();
    }
}
