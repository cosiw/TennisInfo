package TENNIS.TENNISINFO.Player.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Player.Domain.dto.PlayerDTO;
import TENNIS.TENNISINFO.Player.Domain.dto.SNS;
import TENNIS.TENNISINFO.Player.Domain.Enum.BackHandType;
import TENNIS.TENNISINFO.Player.Domain.Enum.ForeHandType;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@NoArgsConstructor
public class Player extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PLAYER_ID")
    private Long playerId;
    @Column(name="RAPID_PLAYER_ID")
    private String rapidPlayerId;
    @Column(name="PLAYER_NAME")
    private String playerName;
    @Column(name="BIRTH")
    private String birth;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="TURNED_PRO")
    private String turnedPro;
    @Column(name="WEIGHT")
    private String weight;
    @Column(name="HEIGHT")
    private String height;
    @Column(name="IMAGE")
    private String image;
    @Column(name="GENDER")
    private String gender;

    public Player(PlayerRapidDTO playerRapidDTO){
        this.rapidPlayerId = playerRapidDTO.getPlayerRapidId();
        this.playerName = playerRapidDTO.getPlayerName();
        this.birth = playerRapidDTO.getBirthDate();
        this.country = playerRapidDTO.getBirthPlace();
        this.turnedPro = playerRapidDTO.getTurnedPro();
        this.weight = playerRapidDTO.getWeight();
        this.height = playerRapidDTO.getHeight();
        //this.image = playerRapidDTO.getImage();
        //this.gender = playerDTO.getGen

    }
}