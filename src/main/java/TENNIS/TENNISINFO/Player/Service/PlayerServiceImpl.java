package TENNIS.TENNISINFO.Player.Service;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Player.Domain.Career;
import TENNIS.TENNISINFO.Player.Domain.dto.PlayerDTO;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Repository.CareerRepository;
import TENNIS.TENNISINFO.Player.Repository.PlayerRepository;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService{

    private RapidApiConfig rapidApiConfig;
    private ObjectMapper objectMapper;
    private PlayerRepository playerRepository;

    private CareerRepository careerRepository;

    @Autowired
    public PlayerServiceImpl(RapidApiConfig rapidApiConfig, ObjectMapper objectMapper, PlayerRepository playerRepository, CareerRepository careerRepository) {
        this.rapidApiConfig = rapidApiConfig;
        this.objectMapper = objectMapper;
        this.playerRepository = playerRepository;
        this.careerRepository = careerRepository;
    }

    @Override
    public void DeletePlayerInfoAndCareer() throws Exception {

    }

    @Override
    public List<String> getRapidIdList(String jsonString) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonString);

        List<String> rapidIdList = new ArrayList<>();
        JsonNode dataArrayNode = rootNode.path("data");

        for(JsonNode dataNode : dataArrayNode){
            RankingDTO rankDto = objectMapper.treeToValue(dataNode, RankingDTO.class);
            rapidIdList.add(rankDto.getRapidPlayerId());
        }
        return rapidIdList;
    }

    @Override
    public String getPlayerByApi(String rapidPlayerId) throws Exception{
        String param = "player_info/" + rapidPlayerId;
        String response = rapidApiConfig.sendUltimateTennisApi(param);
        return response;
    }

    @Override
    public Player savePlayer(String jsonString, String rapidPlayerId) throws Exception{
//        PlayerDTO playerDTO = getPlayerDTO(jsonString, rapidPlayerId);
//
//        Player findPlayer = playerRepository.findByRapidPlayerId(rapidPlayerId)
//            .orElseGet(() -> {
//                Player newPlayer = new Player(playerDTO);
//                return playerRepository.save(newPlayer);
//            });
//
//        return findPlayer;
        return null;
    }

    @Override
    public void saveCareer(String jsonString, String rapidPlayerId, Player player) throws Exception{

        PlayerDTO playerDTO = getPlayerDTO(jsonString, rapidPlayerId);

        Optional<Career> findCareer = careerRepository.findByPlayer(player);

        if(findCareer.isPresent()){
            Career career = findCareer.get();
            career.setCareer(playerDTO, player);
        }else{
            Career newCareer = new Career(playerDTO, player);
            careerRepository.save(newCareer);
        }

    }

    // jsonString, rapidPlayerID로 PlayerDTO 생성 메서드
    public PlayerDTO getPlayerDTO(String jsonString, String rapidPlayerId) throws Exception{
        JsonNode rootNode = objectMapper.readTree(jsonString);

        JsonNode dataNode = rootNode.path("player_data");

        PlayerDTO playerDTO = objectMapper.treeToValue(dataNode, PlayerDTO.class);
        playerDTO.setRapidPlayerId(rapidPlayerId);

        return playerDTO;
    }
}
