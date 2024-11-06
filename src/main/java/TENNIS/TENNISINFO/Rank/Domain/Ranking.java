package TENNIS.TENNISINFO.Rank.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "RANKING")
    private Long ranking;

    @Column(name = "PLAYER_ID", nullable = false)
    private String playerId;

    @Column(name="LIVE_PT")
    private String livePoints;

    @Column(name="POINTS_DIFF")
    private String pointsDiff ="0";
    @Column(name="RANK_DIFF")
    private String rankDiff;
    @Column(name="NEXT_WIN_PT")
    private String nextWinPt;
    @Column(name="CHAMP_PT")
    private String champPt;


    public Ranking(RankingDTO dto){
        this.ranking = Long.parseLong(dto.getRanking());
        this.playerId = dto.getPlayerId();
        Pattern pattern = Pattern.compile("([+-]?\\d{1,3}(?:,\\d{3})*)");
        Matcher matcher = pattern.matcher(dto.getLivePoints());
        List<String> numbers = new ArrayList<>();

        while(matcher.find()){
            numbers.add(matcher.group());
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 == 0) {
                this.livePoints = numbers.get(i);
            } else {
                this.pointsDiff = numbers.get(i);
            }
        }


        this.rankDiff = dto.getRankDiff();
        this.nextWinPt = dto.getNextWinPt().equals("-")?  "0" : dto.getNextWinPt();
        this.champPt = dto.getChampPt().equals("-")?  "0" : dto.getChampPt();
    }
}
