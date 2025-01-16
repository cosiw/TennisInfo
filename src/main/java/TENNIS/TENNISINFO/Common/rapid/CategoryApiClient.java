package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
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
}
