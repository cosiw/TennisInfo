package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingRapidDTO {
    @JsonProperty("team")
    private PlayerRapidDTO team;
    @JsonProperty("ranking")
    private Long curRank;
    @JsonProperty("previousRanking")
    private Long preRank;
    @JsonProperty("points")
    private String point;
    @JsonProperty("bestRanking")
    private Long bestRank;
    @JsonProperty("updateAtTimestamp")
    private String updateTime;


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