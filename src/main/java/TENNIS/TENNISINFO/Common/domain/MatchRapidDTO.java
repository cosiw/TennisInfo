package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchRapidDTO {
    @JsonProperty("tournament")
    private TournamentRapidDTO tournamentRapidDTO;
    @JsonProperty("season")
    private SeasonRapidDTO seasonRapidDTO;
    @JsonProperty("roundInfo")
    private RoundRapidDTO roundRapidDTO;
    @JsonProperty("id")
    private String matchRapidId;
    @JsonProperty("homeTeamSeed")
    private String homeSeed;
    @JsonProperty("awayTeamSeed")
    private String awaySeed;
    @JsonProperty("startTimestamp")
    private String startTimestamp;
    @JsonProperty("homeTeam")
    private PlayerRapidDTO homePlayer;
    @JsonProperty("awayTeam")
    private PlayerRapidDTO awayPlayer;
    @JsonProperty("homeScore")
    private ScoreDTO homeScore;
    @JsonProperty("awayScore")
    private ScoreDTO awayScore;
    @JsonProperty("time")
    private TimeDTO time;
    @JsonProperty("winnerCode")
    private String winnerCode;

}



