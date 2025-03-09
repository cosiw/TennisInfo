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
    private Long point;
    @JsonProperty("previousPoints")
    private Long prePoints;
    @JsonProperty("bestRanking")
    private Long bestRank;
    @JsonProperty("updateAtTimestamp")
    private String updateTime;


}
