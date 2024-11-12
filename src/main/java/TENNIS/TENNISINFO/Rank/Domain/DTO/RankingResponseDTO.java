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
    private String livePt;
    private String pointDiff;


    public RankingResponseDTO(Ranking ranking){
        this.rankingId = ranking.getRankingId();
        this.rapidPlayerId = ranking.getPlayer().getRapidPlayerId();
        this.rank = ranking.getRank();
        this.playerName = ranking.getPlayer().getPlayerName();
        this.country = ranking.getPlayer().getCountry();
        this.livePt = ranking.getLivePoints();
        this.pointDiff = ranking.getPointsDiff();
    }
}
