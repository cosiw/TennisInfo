package TENNIS.TENNISINFO.Player.Domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SNS {
    @JsonProperty("SocialId")
    public String id;
    @JsonProperty("SocialLink")
    public String link;
}
