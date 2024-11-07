package TENNIS.TENNISINFO.Player.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import TENNIS.TENNISINFO.Player.Domain.dto.PlayerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "career")
@Getter
@NoArgsConstructor
public class Career extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CR_ID")
    private Long crId;

    @OneToOne
    @JoinColumn(name="PLAYER_ID")
    private Player player;
    @Column(name="CR_MATCH_LOST_D")
    private String crMatchLostD;
    @Column(name="CR_MATCH_LOST_S")
    private String crMatchLostS;
    @Column(name="CR_MATCH_WIN_D")
    private String crMatchWinD;
    @Column(name="CR_MATCH_WIN_S>")
    private String crMatchWinS;
    @Column(name="CR_HIGH_D")
    private String CrHighD;
    @Column(name="CR_HIGH_S")
    private String CrHighS;
    @Column(name="CR_HIGH_DT_D")
    private String CrHighDtD;
    @Column(name="CR_HIGH_DT_S")
    private String CrHighDtS;
    @Column(name="CR_PRZ")
    private String CrPrz;
    @Column(name="PRZ_YTD_D")
    private String przYtdD;
    @Column(name="PRZ_YTD_S")
    private String przYtdS;
    @Column(name="RANK_D")
    private String rankD;
    @Column(name="RANK_S")
    private String rankS;
    @Column(name="CR_TITLE_D")
    private String CrTitleD;
    @Column(name="CR_TITLE_S")
    private String CrTitleS;
    @Column(name="TITLE_YTD_D")
    private String titleYtdD;
    @Column(name="TITLE_YTD_S")
    private String titleYtdS;
    @Column(name="MATCH_YTD_L_D")
    private String matchYtdLD;
    @Column(name="MATCH_YTD_L_S")
    private String matchYtdLS;
    @Column(name="MATCH_YTD_W_D")
    private String matchYtdWD;
    @Column(name="MATCH_YTD_W_S")
    private String matchYtdWS;

    public Career(PlayerDTO playerDTO){
      this.crMatchLostD = playerDTO.getCrMatchLostD();
      this.crMatchLostS = playerDTO.getCrMatchLostS();
      this.crMatchWinD = playerDTO.getCrMatchWinD();
      this.crMatchWinS = playerDTO.getCrMatchWinS();
      this.CrHighD = playerDTO.getCrHighD();
      this.CrHighS = playerDTO.getCrHighS();
      this.CrHighDtD = playerDTO.getCrHighDtD();
      this.CrHighDtS = playerDTO.getCrHighDtS();
      this.CrPrz = playerDTO.getCrPrz();
      this.przYtdD = playerDTO.getPrzYtdD();
      this.przYtdS = playerDTO.getPrzYtdS();
      this.rankD = playerDTO.getRankD();
      this.rankS = playerDTO.getRankS();
      this.CrTitleD = playerDTO.getCrTitleD();
      this.CrTitleS = playerDTO.getCrTitleS();
      this.titleYtdD = playerDTO.getTitleYtdD();
      this.titleYtdS = playerDTO.getTitleYtdS();
      this.matchYtdLD = playerDTO.getMatchYtdLD();
      this.matchYtdLS = playerDTO.getMatchYtdLS();
      this.matchYtdWD = playerDTO.getMatchYtdWD();
      this.matchYtdWS = playerDTO.getMatchYtdWS();
    }

}
