package TENNIS.TENNISINFO.Player.Domain;

import TENNIS.TENNISINFO.Common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@Getter
@NoArgsConstructor
public class Player extends BaseTimeEntity {

    @Id
    @Column(name="PLAYER_ID")
    private Long playerId;

    @Column(name="RAPID_PLAYER_ID")
    private String rapidPlayerId;
    @Column(name="PLAYER_NAME")
    private String playerName;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="DEBUT_YEAR")
    private String debutYear;
    @Column(name="WEIGHT")
    private String weight;
    @Column(name="HEIGHT")
    private String height;
    @Column(name="FOREHAND")
    private String foreHand;
    @Column(name="BACKHAND")
    private String backHand;
    @Column(name="COACH")
    private String coach;

}