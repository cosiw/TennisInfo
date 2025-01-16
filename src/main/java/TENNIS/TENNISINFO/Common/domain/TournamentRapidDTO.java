package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentRapidDTO {
    @JsonProperty("id")
    private String tournamentRapidId;
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
