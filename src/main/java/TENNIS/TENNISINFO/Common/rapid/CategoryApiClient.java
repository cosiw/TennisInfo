package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryApiClient {
    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;

    public CategoryApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper){
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public List<CategoryRapidDTO> category() throws Exception{
        List<CategoryRapidDTO> list = new ArrayList<>();
        String path = "tennis/tournament/categories";
        String jsonString = rapidApiConfig.sendTennisApi(path);

        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode categoriesNode = rootNode.path("categories");

        for(JsonNode dataNode : categoriesNode){
            CategoryRapidDTO category = objectMapper.treeToValue(dataNode, CategoryRapidDTO.class);
            list.add(category);
        }

        return list;
    }

    public List<TournamentRapidDTO> categoryTournaments(String categoryId) throws Exception{
        List<TournamentRapidDTO> list = new ArrayList<>();
        String path = "tennis/tournament/all/category/" + categoryId;
        String jsonString = rapidApiConfig.sendTennisApi(path);

        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode groupsNode = rootNode.path("groups");
        for(JsonNode group : groupsNode){
            JsonNode tournaments = group.path("uniqueTournaments");
            for(JsonNode tournament : tournaments){
                TournamentRapidDTO tournamentRapidDTO = objectMapper.treeToValue(tournament, TournamentRapidDTO.class);
                list.add(tournamentRapidDTO);
            }
        }

        return list;
    }

}
