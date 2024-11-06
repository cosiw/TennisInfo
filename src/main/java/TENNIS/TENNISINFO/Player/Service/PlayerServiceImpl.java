package TENNIS.TENNISINFO.Player.Service;

import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Player.Domain.DTO.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerServiceImpl implements PlayerService{

    RapidApiConfig rapidApiConfig;

    @Autowired
    public PlayerServiceImpl(RapidApiConfig rapidApiConfig) {
        this.rapidApiConfig = rapidApiConfig;
    }
    @Override
    public String getPlayerByApi(String rapidPlayerId) throws Exception{
        String param = "player_info/" + rapidPlayerId;
        String response = rapidApiConfig.sendUltimateTennisApi(param);
        return response;
    }

    @Override
    public void savePlayer(String jsonString) throws Exception{

    }

    @Override
    public void saveCareer(String jsonString) throws Exception{

    }
}
