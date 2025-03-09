package TENNIS.TENNISINFO.Rank.Domain.DTO;

import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingResponseDTO {

    private Long rankingId;
    private String rapidPlayerId;
    private Long rank;
    private String playerName;
    private String country;
    private Long curPoint;
    private Long prePoint;
    private Long pointDiff;


    public RankingResponseDTO(Ranking ranking){
        this.rankingId = ranking.getRankingId();
        this.rapidPlayerId = ranking.getPlayer().getRapidPlayerId();
        this.rank = ranking.getCurRank();
        this.playerName = ranking.getPlayer().getPlayerName();
        this.country = ranking.getPlayer().getCountry();
        this.curPoint = ranking.getCurPoints();
        this.prePoint = ranking.getPrePoints();
        this.pointDiff = this.curPoint - this.prePoint;
    }
}
