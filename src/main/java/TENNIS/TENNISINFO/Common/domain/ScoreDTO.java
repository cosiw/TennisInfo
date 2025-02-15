package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreDTO {
    @JsonProperty("current")
    private Long current;
    @JsonProperty("display")
    private Long display;
    @JsonProperty("period1")
    private Long score1;
    @JsonProperty("period2")
    private Long score2;
    @JsonProperty("period3")
    private Long score3;
    @JsonProperty("period4")
    private Long score4;
    @JsonProperty("period5")
    private Long score5;
    @JsonProperty("period1TieBreak")
    private Long tieBreak1;
    @JsonProperty("period2TieBreak")
    private Long tieBreak2;
    @JsonProperty("period3TieBreak")
    private Long tieBreak3;
    @JsonProperty("period4TieBreak")
    private Long tieBreak4;
    @JsonProperty("period5TieBreak")
    private Long tieBreak5;
}
