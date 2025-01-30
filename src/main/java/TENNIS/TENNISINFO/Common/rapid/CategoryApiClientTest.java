package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryApiClientTest extends AbstractApiClient<CategoryRapidDTO>{

    public CategoryApiClientTest(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected CategoryRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (CategoryRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<CategoryRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<CategoryRapidDTO>) method.invoke(this, response);
    }

    public List<CategoryRapidDTO> category(String response) throws Exception{
        List<CategoryRapidDTO> list = new ArrayList<>();

        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode categoriesNode = rootNode.path("categories");

        for(JsonNode dataNode : categoriesNode){
            CategoryRapidDTO category = objectMapper.treeToValue(dataNode, CategoryRapidDTO.class);
            list.add(category);
        }

        return list;
    }
}
