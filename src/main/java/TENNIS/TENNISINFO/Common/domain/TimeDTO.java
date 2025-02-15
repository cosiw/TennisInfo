package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeDTO {
    @JsonProperty("period1")
    private String time1;
    @JsonProperty("period2")
    private String time2;
    @JsonProperty("period3")
    private String time3;
    @JsonProperty("period4")
    private String time4;
    @JsonProperty("period5")
    private String time5;
}
