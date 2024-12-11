package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class RankingApiDTO {
    @JsonProperty("team")
    private PlayerApiDTO team;
    @JsonProperty("ranking")
    private Long curRank;
    @JsonProperty("previousRanking")
    private Long preRank;
    @JsonProperty("points")
    private Long point;
    @JsonProperty("bestRanking")
    private Long bestRank;
    private Long updateTime;


}
@Getter
@Setter
@NoArgsConstructor
class PlayerApiDTO{
    @JsonProperty("id")
    private String playerRapidId;
    @JsonProperty("name")
    private String playerName;
}