package TENNIS.TENNISINFO.Rank.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingDTO {

    @JsonProperty("Rank")
    private String ranking;

    @JsonProperty("id")
    private String playerId;

    @JsonProperty("Live Points")
    private String livePoints;

    @JsonProperty("Rank Diff")
    private String rankDiff;

    @JsonProperty("Next win points")
    private String nextWinPt;

    @JsonProperty("Championship Points")
    private String champPt;

}
