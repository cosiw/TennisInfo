package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class TournamentApiClientTest extends AbstractApiClient<TournamentRapidDTO>{

    public TournamentApiClientTest(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected TournamentRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (TournamentRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<TournamentRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<TournamentRapidDTO>) method.invoke(this, response);
    }

    public List<TournamentRapidDTO> categoryTournaments(String response) throws Exception{
        List<TournamentRapidDTO> list = new ArrayList<>();

        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode groupsNode = rootNode.path("groups");
        for(JsonNode group : groupsNode){
            JsonNode tournaments = group.path("uniqueTournaments");
            for(JsonNode tournament : tournaments){
                TournamentRapidDTO tournamentRapidDTO = objectMapper.treeToValue(tournament, TournamentRapidDTO.class);
                // 7월, 8월에 대회를 진행하는 경우 2개 이상 호출할 수 있음.
                boolean flag = list.stream().anyMatch(p -> p.getTournamentRapidId().equals(tournamentRapidDTO.getTournamentRapidId()));
                if(!flag) list.add(tournamentRapidDTO);
            }
        }

        return list;
    }
    public TournamentRapidDTO leagueDetails(String response) throws Exception {

        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode tournamentNode = rootNode.path("uniqueTournament");
        TournamentRapidDTO tournamentDTO = objectMapper.treeToValue(tournamentNode, TournamentRapidDTO.class);

        return tournamentDTO;
    }

    public TournamentRapidDTO tournamentInfo(String response) throws Exception {

        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode tournamentNode = rootNode.path("meta");
        ((ObjectNode)tournamentNode).remove("category");
        TournamentRapidDTO tournamentDTO = objectMapper.treeToValue(tournamentNode, TournamentRapidDTO.class);

        return tournamentDTO;
    }


}
