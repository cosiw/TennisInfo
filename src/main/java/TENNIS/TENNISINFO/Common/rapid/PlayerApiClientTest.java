package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
public class PlayerApiClientTest extends AbstractApiClient<PlayerRapidDTO>{

    public PlayerApiClientTest(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected PlayerRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (PlayerRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<PlayerRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<PlayerRapidDTO>) method.invoke(this, response);
    }

    public PlayerRapidDTO teamDetails(String response) throws Exception {
        PlayerRapidDTO team = new PlayerRapidDTO();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode teamNode = rootNode.path("team");
        JsonNode playerNode = teamNode.path("playerTeamInfo");
        team = objectMapper.treeToValue(playerNode, PlayerRapidDTO.class);
        // 이름
        JsonNode name= teamNode.path("fullName");
        team.setPlayerName(name.asText());
        team.setPlayerRapidId(teamNode.path("id").toString());
        // 상금
        JsonNode prizeNode = playerNode.path("prizeTotalRaw");
        JsonNode curNode = prizeNode.path("currency");

        String cur = curNode.asText();

        if(cur.equals("EUR")){
            Long prizeCurrent = team.getPrizeCurrent() != null? team.getPrizeCurrent() : 0L;
            Long prizeTotal = team.getPrizeTotal() != null? team.getPrizeTotal() : 0L;

            // USD로 저장
            team.setPrizeCurrent(eurToUsd(prizeCurrent));
            team.setPrizeTotal(eurToUsd(prizeTotal));
        }

        return team;


    }

    public String teamImage(String rapidId) throws Exception {
        String path = "tennis/team/" + rapidId + "/image";

        String jsonString = rapidApiConfig.imageApi(path);

        return jsonString;
    }

}
