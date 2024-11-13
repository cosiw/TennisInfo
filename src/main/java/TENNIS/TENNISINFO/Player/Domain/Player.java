package TENNIS.TENNISINFO.Player.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
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
    @Column(name="DEBUT_YEAR")
    private String debutYear;
    @Column(name="WEIGHT")
    private String weight;
    @Column(name="HEIGHT")
    private String height;
    @Column(name="FOREHAND")
    @Enumerated(EnumType.STRING)
    private ForeHandType foreHand;
    @Column(name="BACKHAND")
    @Enumerated(EnumType.STRING)
    private BackHandType backHand;
    @Column(name="COACH")
    private String coach;
    @Column(name="IMAGE")
    private String image;
    @Column(name="SNS1")
    private String instagram;
    @Column(name="SNS2")
    private String Tweeter;

    @OneToOne(mappedBy = "player", fetch = FetchType.LAZY)
    private Career career;

    @OneToOne(mappedBy = "player", fetch = FetchType.LAZY)
    private Ranking ranking;

    public Player(PlayerDTO playerDTO){
        this.rapidPlayerId = playerDTO.getRapidPlayerId();
        this.playerName = playerDTO.getName() + " " + playerDTO.getSurName();
        this.birth = playerDTO.getBirth().substring(0,10);
        this.country = playerDTO.getCountry();
        this.debutYear = playerDTO.getDebutYear();
        this.weight = playerDTO.getWeight();
        this.height = playerDTO.getHeight();
        this.foreHand = ForeHandType.fromString(playerDTO.getForeHand());
        this.backHand = BackHandType.fromString(playerDTO.getBackHand().getVal());
        this.coach = playerDTO.getCoach();
        this.image = playerDTO.getImage();

        List<SNS> snsList = playerDTO.getSnsList();
        for(SNS sns : snsList){
            if(sns.getId().equals("IG")) this.instagram = sns.getLink();
            else this.Tweeter = sns.getLink();
        }
    }
}