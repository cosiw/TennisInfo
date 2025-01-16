package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRapidDTO {
    @JsonProperty("id")
    private String categoryId;
    @JsonProperty("name")
    private String categoryName;

}
