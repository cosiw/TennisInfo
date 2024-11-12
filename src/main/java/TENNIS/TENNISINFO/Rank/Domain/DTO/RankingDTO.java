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
    private String rank;

    @JsonProperty("id")
    private String rapidPlayerId;

    @JsonProperty("Live Points")
    private String livePoints;

    @JsonProperty("Rank Diff")
    private String rankDiff;

    @JsonProperty("Next win points")
    private String nextWinPt;

    @JsonProperty("Championship Points")
    private String champPt;

}
