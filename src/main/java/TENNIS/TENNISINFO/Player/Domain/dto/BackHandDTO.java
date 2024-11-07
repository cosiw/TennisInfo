package TENNIS.TENNISINFO.Player.Domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BackHandDTO {

    @JsonProperty("Description")
    private String val;
    @JsonProperty("Id")
    private String id;
}
