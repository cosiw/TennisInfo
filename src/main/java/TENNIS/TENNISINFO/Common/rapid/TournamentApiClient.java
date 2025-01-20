package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TournamentApiClient {
    private final RapidApiConfig rapidApiConfig;
    private final ObjectMapper objectMapper;

    public TournamentApiClient(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper) {
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
    }

    public List<TournamentRapidDTO> LeagueDetails(List<TournamentRapidDTO> tournamentList) throws Exception {

        List<TournamentRapidDTO> list = new ArrayList<>();

        tournamentList.forEach(tournamentDTO -> {
            String path = "tennis/tournament/" + tournamentDTO.getTournamentRapidId();
            try{

                String jsonString = rapidApiConfig.sendTennisApi(path);
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

                String jsonString = rapidApiConfig.sendTennisApi(path);
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
