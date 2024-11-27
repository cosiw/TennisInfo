package TENNIS.TENNISINFO.Rank.Domain.DTO;

import TENNIS.TENNISINFO.Player.Domain.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopRankingResponseDTO {
    private Long playerId;
    private String rapidPlayerId;
    private String playerName;
    private String birth;
    private String country;
    private String debutYear;
    private String image;
    private String instagram;
    private String tweeter;
    private String rankS;
    private String crPrz;
    private String przYtdS;
    private String titleYtdS;
    private String matchYtdLs;
    private String matchYtdWs;
    private Long rank;
    private String livePoints;
    private String rankDiff;
    private String nextWinPt;
    private String champPt;
    private String winRate;



    public TopRankingResponseDTO(Player p){
        this.playerId = p.getPlayerId();
        this.rapidPlayerId = p.getRapidPlayerId();
        this.playerName = p.getPlayerName();
        this.birth = p.getBirth();
        this.country = p.getCountry();
        this.debutYear = p.getDebutYear();
        this.image = p.getImage();
        this.instagram = p.getInstagram();
        this.tweeter = p.getTweeter();
        this.rankS = p.getCareer().getRankS();
        this.crPrz = p.getCareer().getCrPrz();
        this.przYtdS = p.getCareer().getPrzYtdS();
        this.titleYtdS = p.getCareer().getTitleYtdS();
        this.matchYtdLs = p.getCareer().getMatchYtdLS();
        this.matchYtdWs = p.getCareer().getMatchYtdWS();
        this.rank = p.getRanking().getRank();
        this.livePoints = p.getRanking().getLivePoints();
        this.rankDiff = p.getRanking().getRankDiff();
        this.nextWinPt = p.getRanking().getNextWinPt();
        this.champPt = p.getRanking().getChampPt();

        this.winRate = String.format("%.2f",(Double.parseDouble(this.matchYtdWs) * 100 / (Double.parseDouble(this.matchYtdWs) + Double.parseDouble(this.matchYtdLs))));

    }
}
