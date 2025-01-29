package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
                list.add(tournamentRapidDTO);
            }
        }

        return list;
    }
    public List<TournamentRapidDTO> LeagueDetails(List<TournamentRapidDTO> tournamentList) throws Exception {

        List<TournamentRapidDTO> list = new ArrayList<>();

        tournamentList.forEach(tournamentDTO -> {
            String path = "tennis/tournament/" + tournamentDTO.getTournamentRapidId();
            try{

                //String jsonString = rapidApiConfig.sendTennisApi(path);
                String jsonString = "";
                JsonNode rootNode = objectMapper.readTree(jsonString);
                JsonNode tournamentNode = rootNode.path("uniqueTournament");
                objectMapper.readerForUpdating(tournamentDTO).readValue(tournamentNode);
                list.add(tournamentDTO);
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        return list;
    }

    public List<TournamentRapidDTO> TournamentInfo(List<TournamentRapidDTO> tournamentList) throws Exception {

        List<TournamentRapidDTO> list = new ArrayList<>();


        tournamentList.forEach(tournamentDTO -> {
            String path = "tennis/tournament/" + tournamentDTO.getTournamentRapidId() + "/info";
            try{

                //String jsonString = rapidApiConfig.sendTennisApi(path);
                String jsonString = "";
                JsonNode rootNode = objectMapper.readTree(jsonString);
                JsonNode tournamentNode = rootNode.path("meta");
                ((ObjectNode)tournamentNode).remove("category");
                objectMapper.readerForUpdating(tournamentDTO).readValue(tournamentNode);
                list.add(tournamentDTO);
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        return list;
    }
}
