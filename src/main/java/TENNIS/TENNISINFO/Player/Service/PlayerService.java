package TENNIS.TENNISINFO.Player.Service;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Domain.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {

    void DeletePlayerInfoAndCareer() throws Exception;
    List<String> getRapidIdList(String jsonString) throws Exception;
    String getPlayerByApi(String rapidPlayerId) throws Exception;
    Player savePlayer(String jsonString, String rapidPlayerId) throws Exception;
    void saveCareer(String jsonString, String rapidPlayerId, Player player) throws Exception;




}
