package TENNIS.TENNISINFO.Common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TournamentRapidDTO {
    @JsonProperty("id")
    private String tournamentRapidId;
    @JsonProperty("name")
    private String tournamentName;
    @JsonProperty("category")
    private CategoryRapidDTO category;
    @JsonProperty("titleHolder")
    private PlayerRapidDTO titleHolder;
    @JsonProperty("mostTitles")
    private String mostTitles;
    @JsonProperty("mostTitlesTeams")
    private List<PlayerRapidDTO> mostTitlePlayer;
    @JsonProperty("groundType")
    private String groundType;
    @JsonProperty("tennisPoints")
    private Long points;


}
