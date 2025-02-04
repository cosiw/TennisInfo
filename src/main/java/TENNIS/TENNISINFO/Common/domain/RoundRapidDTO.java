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
public class RoundRapidDTO {
    @JsonProperty("round")
    private String roundRapidId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;

}
