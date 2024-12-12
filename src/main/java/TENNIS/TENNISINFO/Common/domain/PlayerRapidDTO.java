package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerRapidDTO {
    @JsonProperty("id")
    private String playerRapidId;
    @JsonProperty("fullName")
    private String playerName;
    @JsonProperty("birthplace")
    private String birthPlace;
    @JsonProperty("height")
    private String height;
    @JsonProperty("weight")
    private String weight;
    @JsonProperty("plays")
    private String plays;
    @JsonProperty("turnedPro")
    private String turnedPro;
    @JsonProperty("prizeCurrent")
    private Long prizeCurrent;
    @JsonProperty("prizeTotal")
    private Long prizeTotal;
    @JsonProperty("birthDateTimestamp")
    private String birthDate;

}
