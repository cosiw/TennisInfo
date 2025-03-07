package TENNIS.TENNISINFO.Player.Service;

import TENNIS.TENNISINFO.Common.Enum.RapidApi;
import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.rapid.AbstractApiClient;
import TENNIS.TENNISINFO.Common.rapid.PlayerApiClient;
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
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService{

    private AbstractApiClient apiClient;

    private final Map<String, AbstractApiClient> apiClientMap;
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(Map<String, AbstractApiClient> apiClientMap, PlayerRepository playerRepository) {
        this.apiClientMap = apiClientMap;
        this.playerRepository = playerRepository;
    }

    @Override
    public void saveImage(){
        apiClient = apiClientMap.get("playerApiClient");
        PlayerApiClient playerApiClient = (PlayerApiClient) apiClient;

        playerApiClient.imageApiCall(RapidApi.TEAMIMAGE.getUrl("37785"), "37785");

    }


}
