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
public class SeasonRapidDTO {
    @JsonProperty("id")
    private String seasonRapidId;
    @JsonProperty("year")
    private String year;
    @JsonProperty("totalPrizeMoney")
    private Long totalPrizeMoney;
    @JsonProperty("totalPrizeMoneyCurrency")
    private String currency;
    @JsonProperty("numberOfCompetitors")
    private Long round;
}
