package TENNIS.TENNISINFO.Rank.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ranking")
@Getter
@NoArgsConstructor
public class Ranking extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="RANKING_ID")
    private Long rankingId;
    @OneToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;
    @Column(name = "CUR_RANKING")
    private Long curRank;
    @Column(name="PRE_RANKING")
    private Long preRank;
    @Column(name="BEST_RANKING")
    private Long bestRank;
    @Column(name="CUR_POINTS")
    private String curPoints;
    @Column(name="PRE_POINTS")
    private String prePoints;
    @Column(name="RANKING_LAST_UPDATED")
    private String lastUpdate;

    public Ranking(RankingRapidDTO rapidDTO, Player player){

        this.player = player;
        this.curRank = rapidDTO.getCurRank();
        this.preRank = rapidDTO.getPreRank();
        this.bestRank = rapidDTO.getBestRank();
        this.curPoints = rapidDTO.getPoint();
        this.lastUpdate = rapidDTO.getUpdateTime();

    }
}
