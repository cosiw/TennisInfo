package TENNIS.TENNISINFO.Player.Service;

import TENNIS.TENNISINFO.Player.Domain.DTO.PlayerDTO;

public interface PlayerService {

    String getPlayerByApi(String rapidPlayerId) throws Exception;
    void savePlayer(String jsonString) throws Exception;

    void saveCareer(String jsonString) throws Exception;
}
