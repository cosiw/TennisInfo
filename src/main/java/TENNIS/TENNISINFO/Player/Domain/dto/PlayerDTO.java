package TENNIS.TENNISINFO.Player.Domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {

    private String rapidPlayerId;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Surname")
    private String surName;
    @JsonProperty("Birthday")
    private String birth;
    @JsonProperty("Flag Code")
    private String country;
    @JsonProperty("Year Turned Pro")
    private String debutYear;
    @JsonProperty("weight(kgs)")
    private String weight;
    @JsonProperty("height(cm)")
    private String height;
    @JsonProperty("Forehand")
    private String foreHand;
    @JsonProperty("Backhand")
    private BackHandDTO backHand;
    @JsonProperty("Coach")
    private String coach;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("Social Links")
    private List<SNS> snsList;
    @JsonProperty("Career Matches Lost(D)")
    private String crMatchLostD;
    @JsonProperty("Career Matches Lost(S)")
    private String crMatchLostS;
    @JsonProperty("Career Matches Won(D)")
    private String crMatchWinD;
    @JsonProperty("Career Matches Won(S)")
    private String crMatchWinS;
    @JsonProperty("Career high (D)")
    private String CrHighD;
    @JsonProperty("Career High (S)")
    private String CrHighS;
    @JsonProperty("Date career high (D)")
    private String CrHighDtD;
    @JsonProperty("Date career high (S)")
    private String CrHighDtS;
    @JsonProperty("Prize money Career")
    private String CrPrz;
    @JsonProperty("Prize money ytd(D)")
    private String przYtdD;
    @JsonProperty("Prize money ytd(S)")
    private String przYtdS;
    @JsonProperty("Rank(D)")
    private String rankD;
    @JsonProperty("Rank(S)")
    private String rankS;
    @JsonProperty("Title Career (D)")
    private String CrTitleD;
    @JsonProperty("Title Career (S)")
    private String CrTitleS;
    @JsonProperty("Titles ytd (D)")
    private String titleYtdD;
    @JsonProperty("Titles ytd (S)")
    private String titleYtdS;
    @JsonProperty("Ytd Matches Lost(D)")
    private String matchYtdLD;
    @JsonProperty("Ytd Matches Lost(S)")
    private String matchYtdLS;
    @JsonProperty("Ytd Matches Won(D)")
    private String matchYtdWD;
    @JsonProperty("Ytd Matches Won(S)")
    private String matchYtdWS;


}

