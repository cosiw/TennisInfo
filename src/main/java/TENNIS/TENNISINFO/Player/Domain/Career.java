package TENNIS.TENNISINFO.Player.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "career")
@Getter
@NoArgsConstructor
public class Career extends BaseTimeEntity {

    @Id
    @Column(name="CR_ID")
    private Long crId;
    @Column(name="PLAYER_ID")
    private Long playerId;
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


}
