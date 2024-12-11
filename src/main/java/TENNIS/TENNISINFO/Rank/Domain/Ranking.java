package TENNIS.TENNISINFO.Rank.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
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


    public Ranking(RankingDTO dto, Player player){
//        this.rank = Long.parseLong(dto.getRank());
//        this.player = player;
//        Pattern pattern = Pattern.compile("([+-]?\\d{1,3}(?:,\\d{3})*)");
//        Matcher matcher = pattern.matcher(dto.getLivePoints());
//        List<String> numbers = new ArrayList<>();
//
//        while(matcher.find()){
//            numbers.add(matcher.group());
//        }
//        for (int i = 0; i < numbers.size(); i++) {
//            if (i % 2 == 0) {
//                this.livePoints = numbers.get(i);
//            } else {
//                this.pointsDiff = numbers.get(i);
//            }
//        }
//
//
//        this.rankDiff = dto.getRankDiff();
//        this.nextWinPt = dto.getNextWinPt().equals("-")?  "0" : dto.getNextWinPt();
//        this.champPt = dto.getChampPt().equals("-")?  "0" : dto.getChampPt();
    }
}
